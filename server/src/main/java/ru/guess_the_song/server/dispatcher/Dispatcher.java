package ru.guess_the_song.server.dispatcher;

import ru.guess_the_song.core.dto.Request;
import ru.guess_the_song.core.dto.Response;
import ru.guess_the_song.server.controller.Controller;
import ru.guess_the_song.server.net.Session;

public interface Dispatcher {
    <RequestT extends Request, ResponseT extends Response>
    void use(Class<RequestT> requestClass, Controller<RequestT, ResponseT> controller);

    void dispatch(Session session, Object object);
}
