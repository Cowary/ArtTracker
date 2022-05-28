package com.ruderu.arttracker.dbCase.game;

import com.ruderu.arttracker.entity.game.Game;
import com.ruderu.arttracker.repo.game.GameRepo;
import com.ruderu.arttracker.util.DateUtil;
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

    public Game findById(long id) {
        return gameRepo.findById(id).orElseThrow();
    }

    public void save(Game game) {
        game.setLastUpd(DateUtil.now());
        gameRepo.save(game);
    }

    public void delete(Game game) {
        gameRepo.delete(game);
    }
}
