package ru.guess_the_song.server.util;

import ru.guess_the_song.core.dto.Request;
import ru.guess_the_song.core.dto.Response;
import ru.guess_the_song.server.controller.Controller;

import java.util.Map;


/**
 * Я не знаю что это и не хочу больше это трогать
 *
 */
public class Router {
    private Map<Class<Request>, Controller<Request, Response>> routes;

    @SuppressWarnings("unchecked")
    public <T extends Request> void put(Class<T> type, Controller<T, ?> controller) {
        this.routes.put((Class<Request>) type, (Controller<Request, Response>) controller);
    }

    public <T extends Request> Controller<T, ?> get(Class<T> type) {
        @SuppressWarnings("unchecked")
        Controller<T, ?> controller = (Controller<T, ?>) this.routes.get(type);
        return controller;
    }
}
