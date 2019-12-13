package com.johupe.musicplayer.core.scopes

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class ScopeBeanFactoryPostProcessor : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        val scopes = beanFactory.getBeansOfType(BaseScope::class.java)

        for ((key, value) in scopes) {
            beanFactory.registerScope(key, value)
        }
    }
}
