package com.ruderu.arttracker.controller.list;

import com.ruderu.arttracker.dbCase.UserService;
import com.ruderu.arttracker.dbCase.game.GameCrud;
import com.ruderu.arttracker.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PlayListController {

    @Autowired
    GameCrud gameCrud;
    @Autowired
    UserService userService;

    @GetMapping("/title/view/play")
    public String get(@RequestParam(required = false, defaultValue = "") String status,
                      Model model) {

        String nickname = userService.getNameCurrentUser();
        model.addAttribute("nickname", nickname);
        List<Media> mediaList = new ArrayList<>(gameCrud.getAll(status));

        mediaList = mediaList.stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .collect(Collectors.toList());

        model.addAttribute("mediaList", mediaList);
        return "media/view/play";
    }
}
