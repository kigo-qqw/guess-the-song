package ru.guess_the_song.client.ui.components;

import javafx.scene.control.ListView;
import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.PlayerDto;

@Slf4j
public class PlayerList extends ListView<PlayerDto> {
    public PlayerList() {
//        setPrefHeight(getItems().size() * 24 + 2);
    }
}
