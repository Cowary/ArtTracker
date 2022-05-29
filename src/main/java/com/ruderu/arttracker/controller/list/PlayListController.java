package com.ruderu.arttracker.controller.list;

import com.ruderu.arttracker.dbCase.game.GameCrud;
import com.ruderu.arttracker.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlayListController {

    @Autowired
    GameCrud gameCrud;

    @GetMapping("/title/view/play")
    public String get(@RequestParam(required = false, defaultValue = "") String status,
                      Model model) {
        List<Media> mediaList = new ArrayList<>(gameCrud.getAll(status));

        model.addAttribute("mediaList", mediaList);
        return "media/view/play";
    }
}
