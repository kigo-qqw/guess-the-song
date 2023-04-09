package ru.guess_the_song.server.net.impl;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.GetMusicPackDto;
import ru.guess_the_song.core.dto.HealthCheckDto;
import ru.guess_the_song.server.controller.GetMusicPackController;
import ru.guess_the_song.server.controller.HealthCheckController;
import ru.guess_the_song.server.dispatcher.Dispatcher;
import ru.guess_the_song.server.dispatcher.impl.DispatcherImpl;
import ru.guess_the_song.server.net.Session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class SessionImpl implements Session {
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final Dispatcher dispatcher;

    public SessionImpl(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.dispatcher = new DispatcherImpl(); // TODO
        this.dispatcher.use(HealthCheckDto.class, new HealthCheckController());
        this.dispatcher.use(GetMusicPackDto.class, new GetMusicPackController());

    }

    @Override
    public void run() {
        try {
            while (true) {
                Object data = this.in.readObject();
                this.dispatcher.dispatch(this, data);
                log.error("Message: {}", data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                this.in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void send(Object object) {
        try {
            this.out.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
