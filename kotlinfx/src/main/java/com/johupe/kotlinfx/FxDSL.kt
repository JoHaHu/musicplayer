package com.johupe.kotlinfx

import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.media.MediaView
import javafx.util.Callback

@Target(AnnotationTarget.TYPE)
@DslMarker
annotation class DSL

inline fun <reified S> EventTarget.tableview(crossinline f: (@DSL TableView<S>).() -> Unit = {}) = TableView<S>().also {
    tryAttach(it, f)
}

inline fun <reified S, T> TableView<S>.column(text: String = "", crossinline mapper: S.() -> ObservableValue<T>, crossinline f: (@DSL TableColumn<S, T>).() -> Unit = {}): TableColumn<S, T> {
    return TableColumn<S, T>(text).also { column ->
        column.cellValueFactory = Callback { it.value.mapper() }
        column.f()
        columns.add(column)
    }
}


inline fun EventTarget.mediaview(crossinline f: (@DSL MediaView).() -> Unit = {}) = MediaView().also {
    tryAttach(it, f)
}

inline fun EventTarget.label(text: String = "", crossinline f: (@DSL Label).() -> Unit) = Label(text).also {
    tryAttach(it, f)
}

inline fun EventTarget.borderpane(crossinline f: (@DSL BorderPane).() -> Unit = {}) = BorderPane().also {
    tryAttach(it, f)
}

inline fun EventTarget.button(text: String = "", crossinline f: (@DSL Button).() -> Unit = {}) = Button(text).also {
    tryAttach(it, f)
}

inline fun Labeled.image(image: Image, crossinline f: (@DSL ImageView).() -> Unit = {}) = ImageView(image).also {
    tryAttach(it, f)
}

inline fun EventTarget.menubar(crossinline f: (@DSL MenuBar).() -> Unit = {}) = MenuBar().also {
    tryAttach(it, f)
}


inline fun EventTarget.vbox(crossinline f: (@DSL VBox).() -> Unit = {}) = VBox().also {
    tryAttach(it, f)
}

inline fun EventTarget.hbox(crossinline f: (@DSL HBox).() -> Unit = {}) = HBox().also {
    tryAttach(it, f)
}

inline fun EventTarget.tabpane(crossinline f: (@DSL TabPane).() -> Unit) = TabPane().also {
    tryAttach(it, f)
}

inline fun TabPane.tab(text: String = "", crossinline f: (@DSL Tab).() -> Unit = {}) = Tab(text).also {
    it.f()
    tabs.add(it)
}


inline fun MenuBar.menu(text: String = "", crossinline f: (@DSL Menu).() -> Unit = {}) = Menu(text)
        .also {
            it.f()
            menus.add(it)
        }


inline fun Menu.menu(text: String = "", crossinline f: (@DSL Menu).() -> Unit = {}) = Menu(text)
        .also {
            it.f()
            items.add(it)
        }

inline fun Menu.item(label: String = "", crossinline f: (@DSL MenuItem).() -> Unit = {}) = MenuItem(label)
        .also {
            it.f()
            items.add(it)
        }


inline fun <reified T : Node> EventTarget.tryAttach(node: T, crossinline f: T.() -> Unit): T {
    node.f()
    when (this) {
        is Labeled -> {
            if (node is ImageView) {
                graphic = node
            }
        }
        is Tab -> {
            content = node
        }
        is BorderPane -> {
        }
        is Pane -> {
            if (!children.contains(node)) {
                children.add(node)
            }
        }
    }
    return node
}

fun <E> List<E>.observable(): ObservableList<E> = FXCollections.observableArrayList(this)
