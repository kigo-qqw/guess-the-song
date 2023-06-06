package ru.guess_the_song.client.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.PlayerDto;

@Slf4j
@Component
public class PlayerRepository {
    private final ObservableList<PlayerDto> players = FXCollections.observableArrayList();

    public ObservableList<PlayerDto> getAll() {
        return this.players;
    }

    public void add(PlayerDto player) {
        this.players.add(player);
        log.debug("players=" + this.players);
    }
}
