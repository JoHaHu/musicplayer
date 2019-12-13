package com.johupe.kotlinfx

import javafx.beans.property.Property
import kotlin.reflect.KProperty


operator fun <T> Property<T>.getValue(thisRef: Any, kProperty: KProperty<*>): T? {
    return this.value
}

operator fun <T> Property<T>.setValue(thisRef: Any, kProperty: KProperty<*>, t: T) {
    value = t
}

