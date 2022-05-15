package com.ruderu.arttracker.repo.game;

import com.ruderu.arttracker.entity.game.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepo extends CrudRepository<Game, Long> {

    List<Game> findAll();
    Optional<Game> findById(Long id);
    List<Game> findByStatus(String status);
}
