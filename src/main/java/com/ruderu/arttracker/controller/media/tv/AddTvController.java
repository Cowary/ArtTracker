package com.ruderu.arttracker.controller.media.tv;

import com.ruderu.arttracker.entity.tv.Tv;
import com.ruderu.arttracker.repo.tv.TvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddTvController {

    @Autowired
    private TvRepo tvRepo;

    @GetMapping("title/tv/add")
    public String get() {
        return "media/tv/add";
    }

    @PostMapping("title/tv/add")
    public String post(
            @ModelAttribute("tv") Tv tv
    ) {
        //tv.setId(null);
        System.out.println(tv);
        tvRepo.save(tv);

        return "media/tv/add";
    }
}
