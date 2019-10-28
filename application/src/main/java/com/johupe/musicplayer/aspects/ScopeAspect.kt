package com.johupe.musicplayer.aspects

import com.johupe.musicplayer.scopes.ClosesScope
import com.johupe.musicplayer.scopes.OpensScope
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.ApplicationContext


@Configurable
@Aspect
class ScopeAspect {

    @Autowired
    private lateinit var context: ApplicationContext



    @Pointcut(value = "call( * *(..))")
    fun anyMethod() {

    }

    @Before("anyMethod()  && @annotation(opensScope)")
    fun openDialog(opensScope: OpensScope) {
        val scope = context.getBean(opensScope.value.java)
        scope.openScope()
    }

    @After("anyMethod()  && @annotation(closesScope)")
    fun closeDialog(closesScope: ClosesScope) {
        val scope = context.getBean(closesScope.value.java)
        scope.closeScope()
    }
}
