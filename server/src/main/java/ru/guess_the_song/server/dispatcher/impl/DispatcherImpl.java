package ru.guess_the_song.server.dispatcher.impl;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.Request;
import ru.guess_the_song.core.dto.Response;
import ru.guess_the_song.server.controller.Controller;
import ru.guess_the_song.server.dispatcher.Dispatcher;
import ru.guess_the_song.server.net.Session;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DispatcherImpl implements Dispatcher {
    private final Map<Class<Request>, Controller<Request, Response>> routes;

    public DispatcherImpl() {
        this.routes = new HashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RequestT extends Request, ResponseT extends Response> void use(Class<RequestT> type, Controller<RequestT, ResponseT> controller) {
        this.routes.put((Class<Request>) type, (Controller<Request, Response>) controller);
    }

    @Override
    public void dispatch(Session session, Object object) {
        var response = this.routes.get(object.getClass()).request((Request) object);
        session.send(response);
        if (response.isPresent())
            log.error("response: {}", response.get());
        else log.error("response empty");
    }
}
