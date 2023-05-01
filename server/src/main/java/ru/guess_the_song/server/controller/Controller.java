package ru.guess_the_song.server.controller;

import ru.guess_the_song.core.dto.RequestDto;
import ru.guess_the_song.server.net.Session;

//public interface Controller<RequestT extends RequestDto, ResponseT extends ResponseDto> {
//    Result<ResponseT> request(Session session, RequestT request);
//}
public interface Controller<RequestT extends RequestDto> {
    void request(Session session, RequestT request);
}