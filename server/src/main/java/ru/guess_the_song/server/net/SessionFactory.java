package ru.guess_the_song.server.net;

import java.io.IOException;
import java.net.Socket;

public interface SessionFactory {
    Session createSession(Socket socket) throws IOException;
}
