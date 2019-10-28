package com.johupe.musicplayer.scopes


import org.slf4j.Logger
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.config.Scope
import java.util.*
import java.util.Collections.synchronizedMap

abstract class BaseScope(private val logger: Logger) : Scope {
    private val scopedBeans = synchronizedMap(HashMap<String, Any>())
    private val destructionCallbacks = synchronizedMap(HashMap<String, Runnable>())
    var isState = false
        private set

    override fun resolveContextualObject(key: String): Any? {
        return null
    }

    override fun remove(name: String): Any? {
        destructionCallbacks.remove(name)
        return scopedBeans.remove(name)
    }

    override fun get(name: String, objectFactory: ObjectFactory<*>): Any {
        if (isState) {
            if (!scopedBeans.containsKey(name)) {
                scopedBeans[name] = objectFactory.getObject()
                logger.debug("Bean $name created")
            }
            return scopedBeans[name]!!
        } else {
            throw ScopeNotActiveException("couldn't get Bean $name. ${javaClass.canonicalName} is closed")
        }
    }

    override fun registerDestructionCallback(name: String, callback: Runnable) {
        destructionCallbacks[name] = callback
    }

    override fun getConversationId(): String? {
        return null
    }

    fun openScope() {
        isState = true
        logger.info("${javaClass.canonicalName} opened")
    }

    fun closeScope() {
        synchronized(this) {
            for (destructionCallback in destructionCallbacks.values) {
                destructionCallback.run()
            }
            scopedBeans.clear()
            isState = false
            logger.info("${javaClass.canonicalName} closed")
        }
    }
}


