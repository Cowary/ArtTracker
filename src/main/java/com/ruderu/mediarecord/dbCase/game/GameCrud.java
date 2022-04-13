package com.ruderu.mediarecord.dbCase.game;

import com.ruderu.mediarecord.entity.game.Game;
import com.ruderu.mediarecord.repo.game.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameCrud {

    @Autowired
    GameRepo gameRepo;

    public List<Game> getAll(String status) {
        if(status.equals("")) return gameRepo.findAll();
        else return gameRepo.findByStatus(status);
    }
}
