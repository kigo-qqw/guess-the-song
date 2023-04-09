module ru.guess_the_song.server {
    requires ru.guess_the_song.core;

    requires org.slf4j;
    requires org.apache.logging.log4j;
    requires static lombok;

    exports ru.guess_the_song.server;
}