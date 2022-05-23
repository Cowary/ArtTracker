package com.ruderu.arttracker.controller.media.game;

import com.ruderu.arttracker.dbCase.game.GameCrud;
import com.ruderu.arttracker.entity.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditGameController {

    @Autowired
    GameCrud gameCrud;

    @GetMapping("title/game/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        Game game = gameCrud.findById(id);

        model.addAttribute("game", game);
        model.addAttribute("edit", true);

        return "media/game/add";
    }

    @PostMapping("title/game/edit")
    public String post(
            @ModelAttribute("game") Game game
    ) {
        gameCrud.save(game);

        return "redirect:../view/media";
    }

    @PostMapping("title/game/delete")
    public String post(
            @RequestParam() long id
    ) {
        Game game = gameCrud.findById(id);
        gameCrud.delete(game);

        return "redirect:../view/media";
    }
}
