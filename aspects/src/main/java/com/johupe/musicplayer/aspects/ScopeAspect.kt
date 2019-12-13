package com.johupe.musicplayer.aspects

import com.johupe.musicplayer.core.scopes.ClosesScope
import com.johupe.musicplayer.core.scopes.OpensScope
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

    //Field injection because no arg constructor needed
    @Autowired
    private lateinit var context: ApplicationContext

    @Pointcut(value = "execution(* *(..))")
    fun anyMethod() {

    }

    @Before("anyMethod() && @annotation(scope)")
    fun openDialog(scope: OpensScope) {
        val bean = context.getBean(scope.value.java)
        bean.openScope()
    }

    @After("anyMethod() && @annotation(scope)")
    fun closeDialog(scope: ClosesScope) {
        val bean = context.getBean(scope.value.java)
        bean.closeScope()
    }

}
