package ru.guess_the_song.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.PlayerRepository;
import ru.guess_the_song.core.dto.EntityDto;
import ru.guess_the_song.core.dto.PlayerJoinGameNotificationDto;

import java.io.*;
import java.net.Socket;

@Slf4j
@Component
public class ConnectionService {
    private Socket socket;
    //    private final Map<Class<EntityDto>, List<EventListener>> listeners = new HashMap<>();
    private Connection connection;
    private Thread worker;
    private final PlayerRepository playerRepository;


//    private ObjectInputStream ois;
//    private ObjectOutputStream oos;

    private EntityDto lastEntity = null;

    public ConnectionService(
            PlayerRepository playerRepository
    ) {
        this.playerRepository = playerRepository;
    }
//    private long lastEntityTime = -1;

    public void connect(@Value("${server.address}") String address, @Value("${server.port}") int port) throws IOException {
        this.socket = new Socket(address, port);

        this.connection = new Connection(this.socket);
        this.worker = new Thread(this.connection);
        worker.start();
    }


    public void send(Object object) throws IOException {
        if (this.connection != null)
            this.connection.send(object);
        else throw new RuntimeException();
    }

    public <T extends EntityDto> T waitObject(Class<T> entityClass)  {
        while (true) {
            log.debug("class=" + entityClass);
            if (entityClass.isInstance(lastEntity)) {
                return (T) this.lastEntity;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//                try {
//                    connection.wait();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

        }
    }


    //    @Slf4j
    @Component
    private class Connection implements Runnable {
        private boolean isRun = true;
        private final Socket socket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

        public Connection(Socket socket) throws IOException {
            this.socket = socket;
//            this.ois = new ObjectInputStream(socket.getInputStream());
//            this.oos = new ObjectOutputStream(socket.getOutputStream());
        }

        public void stop() {
            this.isRun = false;
        }

        @Override
        public void run() {
            log.debug("CONNECTION RUN");
            try {
                OutputStream os = this.socket.getOutputStream();
                InputStream is = this.socket.getInputStream();
                this.oos = new ObjectOutputStream(os);
                this.ois = new ObjectInputStream(is);

                log.debug("ObjectStreams created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            while (this.isRun) {
                try {
                    Object object = this.ois.readObject();
                    lastEntity = (EntityDto) object;
                    log.debug("lastEntity=" + lastEntity);

                    if (object instanceof PlayerJoinGameNotificationDto playerJoinGameNotificationDto) {
                        playerRepository.add(playerJoinGameNotificationDto.getPlayer());
                    }

                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void send(Object object) throws IOException {
            this.oos.writeObject(object);
        }
    }
}
