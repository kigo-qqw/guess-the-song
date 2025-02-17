package ru.guess_the_song.client.service;

import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.PlayerRepository;
import ru.guess_the_song.client.ui.ScreenManager;
import ru.guess_the_song.client.ui.controller.DisconnectController;
import ru.guess_the_song.client.ui.controller.GameController;
import ru.guess_the_song.core.dto.*;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

@Slf4j
@Component
public class ConnectionService {
    private Socket socket;
    private Connection connection;
    private Thread worker;
    private final PlayerRepository playerRepository;
    private ScreenManager screenManager;
    private EntityDto lastEntity = null;

    public ConnectionService(
            PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void connect(@Value("${server.address}") String address, @Value("${server.port}") int port) throws IOException {
        this.socket = new Socket(address, port);

        this.connection = new Connection(this.socket);
        this.worker = new Thread(this.connection);
        worker.start();
    }


    public void send(Object object) throws IOException {
        if (this.connection != null)
            this.connection.send(object);
        else {
            Platform.runLater(() -> screenManager.openDialogWindow(DisconnectController.class));
        }
//        throw new RuntimeException();
    }

    public <T extends EntityDto> T waitObject(Class<T> entityClass) {
        while (true) {
            log.debug("class=" + entityClass);
            if (entityClass.isInstance(lastEntity)) {
                return (T) this.lastEntity;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setScreenManager(ScreenManager screenManager) {
        log.debug("set screenManager=" + screenManager);
        this.screenManager = screenManager;
    }

    @Component
    private class Connection implements Runnable {
        private boolean isRun = true;
        private final Socket socket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private GameController gameController = null;

        public Connection(Socket socket) {
            this.socket = socket;
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
            } catch (IOException e) {
                Platform.runLater(() -> screenManager.openDialogWindow(DisconnectController.class));
            }

            while (this.isRun) {
                try {
                    Object object = this.ois.readObject();
                    lastEntity = (EntityDto) object;
                    log.debug("lastEntity=" + lastEntity);

                    if (object instanceof PlayerJoinGameNotificationDto playerJoinGameNotificationDto) {
                        playerRepository.add(playerJoinGameNotificationDto.getPlayer());
                    }
                    if (object instanceof PlayerLeftGameNotificationDto playerLeftGameNotificationDto) {
                        playerRepository.remove(playerLeftGameNotificationDto.getPlayer());
                    }
                    if (object instanceof StartGameNotificationDto) {
                        if (screenManager != null)
                            Platform.runLater(() ->
                                    gameController = screenManager.changeWindow(GameController.class));
                    }
                    if (object instanceof StartRoundDto startRoundDto) {
                        Platform.runLater(() -> {
                            if (gameController != null)
                                gameController.startRound(startRoundDto);
                        });
                    }
                    if (object instanceof EndRoundDto endRoundDto) {
                        if (gameController != null) {
                            Platform.runLater(() -> {
                                playerRepository.update(Arrays.asList(endRoundDto.getPlayers()));
                                gameController.endRound(endRoundDto);
                            });
                        }
                    }
                    if (object instanceof GameFinishedDto gameFinishedDto) {
                        if (gameController != null) {
                            Platform.runLater(() -> {
                                playerRepository.update(Arrays.asList(gameFinishedDto.getGame().getPlayers()));
                                gameController.finishGame(gameFinishedDto);
                            });
                        }
                    }


                } catch (IOException | ClassNotFoundException e) {
//                    throw new RuntimeException(e);
                    Platform.runLater(() -> screenManager.openDialogWindow(DisconnectController.class));
                    return;
                }
            }
        }

        public void send(Object object) {
            try {
                this.oos.writeObject(object);
            } catch (IOException e) {
                Platform.runLater(() -> screenManager.openDialogWindow(DisconnectController.class));
            }
        }
    }
}
