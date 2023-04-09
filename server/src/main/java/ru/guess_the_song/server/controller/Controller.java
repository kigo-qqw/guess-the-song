package ru.guess_the_song.server.controller;

import ru.guess_the_song.core.dto.Request;
import ru.guess_the_song.core.dto.Response;
import ru.guess_the_song.core.dto.Result;

public interface Controller<RequestT extends Request, ResponseT extends Response> {
    Result<ResponseT> request(RequestT request);
}
