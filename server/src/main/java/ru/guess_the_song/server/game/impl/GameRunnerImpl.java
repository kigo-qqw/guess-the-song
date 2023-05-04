package ru.guess_the_song.server.game.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.game.GameRunner;

@Slf4j
@Component
public class GameRunnerImpl implements GameRunner {
    private final Game game;

    public GameRunnerImpl(Game game) {
        this.game = game;
    }

    @Override
    public void run() {

        boolean run = true;
        int songIdx = 0;
        while (run) {
//            MusicPack musicPack = game.get().getMusicPack();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("Игровой цикл");
        }

    }
}
