package com.johupe.musicplayer.aspects

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Configurable

@Configurable
@Aspect
class TracingAspect {


    private val logger = LoggerFactory.getLogger(TracingAspect::class.java)

    @Pointcut("execution(* *(..))")
    fun anyMethod() {

    }

    @Before("anyMethod()")
    fun logBefore(jp: JoinPoint) {
        logger.trace("entering {}", jp.signature)
    }

    @After("anyMethod()")
    fun logAfter(jp: JoinPoint) {
        logger.trace("leaving {}", jp.signature)
    }


}
