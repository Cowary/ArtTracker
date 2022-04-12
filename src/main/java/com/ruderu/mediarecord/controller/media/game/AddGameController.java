package com.ruderu.mediarecord.controller.media.game;

import com.ruderu.mediarecord.entity.game.Game;
import com.ruderu.mediarecord.repo.game.GameRepo;
import com.ruderu.mediarecord.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

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
            @ModelAttribute("game") Game game,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate
    ) {
        if(startDate != null) {
            game.setStartDate(startDate);
        }
        if(endDate != null) {
            game.setEndDate(endDate);
        }
        System.out.println(game);
        gameRepo.save(game);

        return "media/game/add";
    }
}
