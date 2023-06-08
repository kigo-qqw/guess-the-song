module ru.guess_the_song.server {
    requires ru.guess_the_song.core;

    requires org.slf4j;
    requires org.apache.logging.log4j;
    requires org.hibernate.orm.core;
    requires org.hibernate.orm.jpamodelgen;
    requires jakarta.persistence;
    requires spring.context;
    requires spring.beans;
    requires static lombok;
    requires spring.core;

    opens ru.guess_the_song.server.entity to jakarta.persistence;

    exports ru.guess_the_song.server;
    exports ru.guess_the_song.server.net;
}