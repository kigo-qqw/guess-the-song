module ru.guess_the_song.server {
    requires ru.guess_the_song.core;

    requires org.slf4j;
    requires org.apache.logging.log4j;
    requires org.hibernate.orm.core;
    requires org.hibernate.orm.jpamodelgen;
//    requires static org.hibernate.orm.jpamodelgen;
    requires static lombok;

    exports ru.guess_the_song.server;
}