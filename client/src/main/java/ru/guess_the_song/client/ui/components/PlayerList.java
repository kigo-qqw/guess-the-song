package ru.guess_the_song.client.ui.components;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.core.dto.PlayerDto;

@Slf4j
@Component
public class PlayerList extends ListView<PlayerDto> {
    private UserService userService;
    private boolean isShowPoints = false;

    public void init(UserService userService, boolean isShowPoints) {
        this.userService = userService;
        this.isShowPoints = isShowPoints;
        setCellFactory((listView) -> new ListCell<>() {
            @Override
            public void updateItem(PlayerDto playerDto, boolean empty) {
                super.updateItem(playerDto, empty);
                if (empty || playerDto == null) {
//                    if (isShowPoints) setText("unknown - null");
//                    else setText("unknown");
                    setText(null);
                } else {
//                    String username = userService.getById(playerDto.getUser().getId())
//                            .map(UserDto::getUsername)
//                            .orElse("unknown");
                    String username = playerDto.getUser().getUsername();
                    if (isShowPoints) setText(username + " - " + playerDto.getPoints());
                    else setText(username);
                }
            }
        });
    }
}
