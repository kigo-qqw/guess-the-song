package ru.guess_the_song.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.EntityDto;
import ru.guess_the_song.core.dto.RequestDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

@Slf4j
@Component
public class ConnectionService {
    private Socket socket;
//    private final Map<Class<EntityDto>, List<EventListener>> listeners = new HashMap<>();
//    private Connection connection;


    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public void connect(@Value("${server.address}") String address, @Value("${server.port}") int port) throws IOException {
//        if (this.connection != null)
//            this.connection.stop();
        this.socket = new Socket(address, port);
        log.debug("address=" + address);
        log.debug("port=" + port);

        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
//        this.connection = new Connection(this.socket);
//        new Thread(this.connection).start();
    }

//    @SuppressWarnings("unchecked")
//    public <T extends EntityDto> void addEventListener(Class<T> event, EventListener eventListener) {
//        if (!this.listeners.containsKey(event))
//            this.listeners.put((Class<EntityDto>) event, new LinkedList<>());
//        this.listeners.get((Class<EntityDto>) event).add(eventListener);
//    }

    public void send(Object object) throws IOException {
        this.oos.writeObject(object);
//        if (this.connection != null)
//            this.connection.send(object);
//        else throw new RuntimeException();
    }

    public Object waitObject() throws IOException, ClassNotFoundException {
        return this.ois.readObject();
    }


//    private class Connection implements Runnable {
//        private boolean run = true;
//        private final ObjectInputStream ois;
//        private final ObjectOutputStream oos;
//
//        public Connection(Socket socket) throws IOException {
//            this.ois = new ObjectInputStream(socket.getInputStream());
//            this.oos = new ObjectOutputStream(socket.getOutputStream());
//        }
//
//        public void stop() {
//            this.run = false;
//        }
//
//        public void send(Object object) {
//            try {
//                this.oos.writeObject(object);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        @Override
//        public void run() {
//            while (run) {
//                try {
//                    Object object = this.ois.readObject();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
}
