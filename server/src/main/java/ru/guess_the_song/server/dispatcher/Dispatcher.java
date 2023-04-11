package ru.guess_the_song.server.dispatcher;

import ru.guess_the_song.core.dto.RequestDto;
import ru.guess_the_song.core.dto.ResponseDto;
import ru.guess_the_song.server.controller.Controller;
import ru.guess_the_song.server.net.Session;

public interface Dispatcher {
    <RequestT extends RequestDto, ResponseT extends ResponseDto>
    void use(Class<RequestT> requestClass, Controller<RequestT, ResponseT> controller);

    void dispatch(Session session, Object object);
}
