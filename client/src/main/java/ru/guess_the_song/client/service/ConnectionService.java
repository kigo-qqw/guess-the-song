package ru.guess_the_song.client.service;

import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.EntityDto;

import java.io.*;
import java.net.Socket;

@Slf4j
@Component
public class ConnectionService {
    public Socket socket;
    //    private final Map<Class<EntityDto>, List<EventListener>> listeners = new HashMap<>();
    private Connection connection;
    private Thread worker;


//    private ObjectInputStream ois;
//    private ObjectOutputStream oos;

    private EntityDto lastEntity = null;
//    private long lastEntityTime = -1;

    public void connect(@Value("${server.address}") String address, @Value("${server.port}") int port) throws IOException {
//        if (this.connection != null)
//            this.connection.stop();
        this.socket = new Socket(address, port);
        log.debug("address=" + address);
        log.debug("port=" + port);

        this.connection = new Connection(this.socket);
        this.worker = new Thread(this.connection);
        worker.start();
        log.debug("connected=" + this.connection);
    }


    public void send(Object object) throws IOException {
//        this.oos.writeObject(object);
        log.debug("send " + object);
        if (this.connection != null)
            this.connection.send(object);
        else throw new RuntimeException();
    }

    public <T extends EntityDto> T waitObject(Class<T> entityClass) {
//        long time = System.currentTimeMillis();
        while (true) {
            log.debug("class=" + entityClass);
            if (entityClass.isInstance(lastEntity)) {
                return (T) this.lastEntity;
            }
//                try {
//                    connection.wait();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

        }
//        return (T) lastEntity;
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
                log.debug(os.toString());
                InputStream is = this.socket.getInputStream();
                log.debug(is.toString());
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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void send(Object object) throws IOException {
            this.oos.writeObject(object);
        }
    }
}
