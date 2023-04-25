package ru.guess_the_song.server.dispatcher.impl;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.RequestDto;
import ru.guess_the_song.core.dto.ResponseDto;
import ru.guess_the_song.server.controller.Controller;
import ru.guess_the_song.server.dispatcher.Dispatcher;
import ru.guess_the_song.server.net.Session;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DispatcherImpl implements Dispatcher {
    private final Map<Class<RequestDto>, Controller<RequestDto, ResponseDto>> routes;

    public DispatcherImpl() {
        this.routes = new HashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RequestT extends RequestDto, ResponseT extends ResponseDto> void use(Class<RequestT> type, Controller<RequestT, ResponseT> controller) {
        this.routes.put((Class<RequestDto>) type, (Controller<RequestDto, ResponseDto>) controller);
    }

    @Override
    public void dispatch(Session session, Object object) {
        var response = this.routes.get(object.getClass()).request(session, (RequestDto) object);
        session.send(response);
        if (response.isPresent())
            log.debug("response: {}", response.get());
        else log.debug("response empty");
    }
}
