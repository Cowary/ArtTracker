package com.ruderu.mediarecord.repo.game;

import com.ruderu.mediarecord.entity.game.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepo extends CrudRepository<Game, Long> {
}
