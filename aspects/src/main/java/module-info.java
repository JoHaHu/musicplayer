open module musicplayer.aspects {
    requires org.aspectj.weaver;
    requires spring.beans;
    requires spring.context;
    requires kotlin.stdlib;
    requires musicplayer.core;
    requires org.slf4j;
    requires spring.aspects;
    requires spring.boot;
    requires spring.boot.autoconfigure;
}
