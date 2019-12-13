open module musicplayer.service {
    requires kotlin.stdlib;
    requires javafx.media;
    requires spring.data.jpa;
    requires spring.context;
    requires org.slf4j;
    requires musicplayer.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires java.sql;
    exports com.johupe.musicplayer.domain;
    exports com.johupe.musicplayer.domain.service;
    exports com.johupe.musicplayer.domain.model.entities;
    exports com.johupe.musicplayer.domain.model.media;

}
