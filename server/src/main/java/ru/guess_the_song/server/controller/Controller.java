package ru.guess_the_song.server.controller;

import ru.guess_the_song.core.dto.RequestDto;
import ru.guess_the_song.core.dto.ResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.server.net.Session;

public interface Controller<RequestT extends RequestDto, ResponseT extends ResponseDto> {
    Result<ResponseT> request(Session session, RequestT request);
}
