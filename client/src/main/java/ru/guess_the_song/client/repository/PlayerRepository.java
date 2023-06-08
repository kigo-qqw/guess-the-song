package ru.guess_the_song.client.repository;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.core.dto.GetPlayerDto;
import ru.guess_the_song.core.dto.GetPlayerResponseDto;
import ru.guess_the_song.core.dto.PlayerDto;
import ru.guess_the_song.core.dto.Status;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PlayerRepository {
    private ConnectionService connectionService;
    private final ObservableList<PlayerDto> players = FXCollections.observableArrayList();

    public PlayerRepository(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public ObservableList<PlayerDto> getAll() {
        return this.players;
    }

    public synchronized void add(PlayerDto player) {
        Platform.runLater(() -> this.players.add(player));
//        this.players.add(player);
        log.debug("players=" + this.players);
    }

    public  Optional<PlayerDto> get(UUID id) {
        GetPlayerResponseDto getPlayerResponseDto;
        try {
            this.connectionService.send(GetPlayerDto.builder().id(id).build());
            getPlayerResponseDto = this.connectionService.waitObject(GetPlayerResponseDto.class);

            log.debug(String.valueOf(getPlayerResponseDto));
        } catch (IOException e) {
            return Optional.empty();
        }
//        if (createUserResponseDto == null) return Optional.empty();
        if (getPlayerResponseDto.getStatus() == Status.ERROR) return Optional.empty();
        return Optional.of(getPlayerResponseDto.getPlayer());
    }

    public void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public synchronized void update(List<PlayerDto> players) {
        Platform.runLater(() -> this.players.setAll(players));
    }
}
