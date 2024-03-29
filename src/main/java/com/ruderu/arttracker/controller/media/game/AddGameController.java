package com.ruderu.arttracker.controller.media.game;

import com.ruderu.arttracker.dbCase.game.GameCrud;
import com.ruderu.arttracker.entity.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddGameController {

    @Autowired
    GameCrud gameCrud;

    @GetMapping("title/game/add")
    public String get(
            Model model
    ) {
        return "media/game/add";
    }

    @PostMapping("title/game/add")
    public String post(
            @ModelAttribute("game") Game game,
            RedirectAttributes redirectAttributes
    ) {
        gameCrud.save(game);
        redirectAttributes.addAttribute("id", game.getId());

        return "redirect:../game/edit";
    }
}
