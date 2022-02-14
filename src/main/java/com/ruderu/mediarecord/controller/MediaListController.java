package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.dbCase.AnimeCrud;
import com.ruderu.mediarecord.dbCase.MangaCrud;
import com.ruderu.mediarecord.dbCase.RanobeCrud;
import com.ruderu.mediarecord.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MediaListController {

    @Autowired
    AnimeCrud animeCrud;
    @Autowired
    MangaCrud mangaCrud;
    @Autowired
    RanobeCrud ranobeCrud;

    @GetMapping("/title/media")
    public String get(@RequestParam(required = false, defaultValue = "") String status,
            Model model) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(animeCrud.getAll(status));
        mediaList.addAll(mangaCrud.getAll(status));
        mediaList.addAll(ranobeCrud.getAll(status));
        model.addAttribute("mediaList", mediaList);
        return "mediaList";
    }
}
