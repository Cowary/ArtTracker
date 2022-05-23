package com.ruderu.arttracker.controller.media.tv;

import com.ruderu.arttracker.dbCase.tv.TvCrud;
import com.ruderu.arttracker.entity.tv.Tv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditTvController {

    @Autowired
    TvCrud tvCrud;

    @GetMapping("title/tv/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        Tv tv = tvCrud.findById(id);

        model.addAttribute("tv", tv);
        model.addAttribute("edit", true);

        return "media/tv/add";
    }

    @PostMapping("title/tv/edit")
    public String post(
            @ModelAttribute("tv") Tv tv
    ) {
        tvCrud.save(tv);

        return "redirect:../view/media";
    }

    @PostMapping("title/tv/delete")
    public String post(
            @RequestParam() long id
    ) {
        Tv tv = tvCrud.findById(id);
        tvCrud.delete(tv);

        return "redirect:../view/media";
    }
}
