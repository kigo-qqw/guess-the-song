package ru.guess_the_song.client.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.core.dto.*;

import java.io.IOException;
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

    public void add(PlayerDto player) {
        this.players.add(player);
        log.debug("players=" + this.players);
    }

    public Optional<PlayerDto> get(UUID id) {
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
}
