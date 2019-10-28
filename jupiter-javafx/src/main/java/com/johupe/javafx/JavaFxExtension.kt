package com.johupe.javafx


import javafx.application.Platform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.extension.*
import java.lang.reflect.Constructor
import java.lang.reflect.Method

internal class JavaFxExtension : InvocationInterceptor, BeforeAllCallback, AfterAllCallback {

    override fun beforeAll(context: ExtensionContext) = Platform.startup { }

    override fun afterAll(context: ExtensionContext) = Platform.exit()

    @Throws(Throwable::class)
    override fun <T> interceptTestClassConstructor(invocation: InvocationInterceptor.Invocation<T>, invocationContext: ReflectiveInvocationContext<Constructor<T>>?, extensionContext: ExtensionContext?): T =
            runLaterAndWait { super.interceptTestClassConstructor(invocation, invocationContext, extensionContext) }

    @Throws(Throwable::class)
    override fun interceptBeforeAllMethod(invocation: InvocationInterceptor.Invocation<Void>, invocationContext: ReflectiveInvocationContext<Method>?, extensionContext: ExtensionContext?) =
            runLaterAndWait {
                super.interceptBeforeAllMethod(invocation, invocationContext, extensionContext)
            }

    @Throws(Throwable::class)
    override fun interceptBeforeEachMethod(invocation: InvocationInterceptor.Invocation<Void>, invocationContext: ReflectiveInvocationContext<Method>?, extensionContext: ExtensionContext?) =
            runLaterAndWait {
                super.interceptBeforeEachMethod(invocation, invocationContext, extensionContext)
            }

    @Throws(Throwable::class)
    override fun interceptTestMethod(invocation: InvocationInterceptor.Invocation<Void>, invocationContext: ReflectiveInvocationContext<Method>?, extensionContext: ExtensionContext?) =
            runLaterAndWait {
                super.interceptTestMethod(invocation, invocationContext, extensionContext)
            }

    @Throws(Throwable::class)
    override fun <T> interceptTestFactoryMethod(invocation: InvocationInterceptor.Invocation<T>, invocationContext: ReflectiveInvocationContext<Method>?, extensionContext: ExtensionContext?): T =
            runLaterAndWait { super.interceptTestFactoryMethod(invocation, invocationContext, extensionContext) }

    @Throws(Throwable::class)
    override fun interceptTestTemplateMethod(invocation: InvocationInterceptor.Invocation<Void>, invocationContext: ReflectiveInvocationContext<Method>?, extensionContext: ExtensionContext?) {
        runLaterAndWait<Any> {
            super.interceptTestTemplateMethod(invocation, invocationContext, extensionContext)
        }
    }

    @Throws(Throwable::class)
    override fun interceptDynamicTest(invocation: InvocationInterceptor.Invocation<Void>, extensionContext: ExtensionContext?) =
            runLaterAndWait {
                super.interceptDynamicTest(invocation, extensionContext)
            }

    @Throws(Throwable::class)
    override fun interceptAfterEachMethod(invocation: InvocationInterceptor.Invocation<Void>, invocationContext: ReflectiveInvocationContext<Method>?, extensionContext: ExtensionContext?) =
            runLaterAndWait {
                super.interceptAfterEachMethod(invocation, invocationContext, extensionContext)

            }

    @Throws(Throwable::class)
    override fun interceptAfterAllMethod(invocation: InvocationInterceptor.Invocation<Void>, invocationContext: ReflectiveInvocationContext<Method>?, extensionContext: ExtensionContext?) =
            runLaterAndWait {
                super.interceptAfterAllMethod(invocation, invocationContext, extensionContext)

            }

    @Throws(Throwable::class)
    fun <T> runLaterAndWait(function: () -> T): T {
        return runBlocking {
            val job = async(Dispatchers.JavaFx) { function.invoke() }
            return@runBlocking job.await()
        }
    }


}


