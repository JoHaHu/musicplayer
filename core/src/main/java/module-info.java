open module musicplayer.core {
    requires spring.context;
    requires org.slf4j;
    requires kotlin.stdlib;
    requires spring.beans;
    requires spring.boot;
    requires javafx.graphics;
    exports com.johupe.musicplayer.core.scopes;
    exports com.johupe.musicplayer.core.exceptions;
    exports com.johupe.musicplayer.core.events;
}
