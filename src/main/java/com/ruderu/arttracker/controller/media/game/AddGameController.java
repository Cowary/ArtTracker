package com.ruderu.arttracker.controller.media.game;

import com.ruderu.arttracker.entity.game.Game;
import com.ruderu.arttracker.repo.game.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddGameController {

    @Autowired
    GameRepo gameRepo;

    @GetMapping("title/game/add")
    public String get(
            Model model
    ) {
        return "media/game/add";
    }

    @PostMapping("title/game/add")
    public String post(
            @ModelAttribute("game") Game game
    ) {
        System.out.println(game);
        gameRepo.save(game);

        return "media/game/add";
    }
}
