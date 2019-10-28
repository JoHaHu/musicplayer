open module musicplayer {

    requires spring.aop;
    requires spring.aspects;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires spring.context;

    requires spring.boot.starter.data.jpa;
    requires jakarta.activation;
    requires java.xml.bind;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;

    requires java.sql;
    requires jdk.unsupported;

    requires javafx.controls;
    requires org.slf4j;
    requires kotlin.stdlib;
    requires org.aspectj.weaver;


}
