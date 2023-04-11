package ru.guess_the_song.server.controller;

import ru.guess_the_song.core.dto.RequestDto;
import ru.guess_the_song.core.dto.ResponseDto;
import ru.guess_the_song.core.dto.Result;

public interface Controller<RequestT extends RequestDto, ResponseT extends ResponseDto> {
    Result<ResponseT> request(RequestT request);
}
