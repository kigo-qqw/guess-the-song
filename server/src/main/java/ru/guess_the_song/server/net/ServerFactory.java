package ru.guess_the_song.server.net;

import java.io.IOException;

public interface ServerFactory {
    Server createServer() throws IOException;
}
