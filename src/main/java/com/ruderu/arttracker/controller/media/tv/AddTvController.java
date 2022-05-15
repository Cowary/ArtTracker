package com.ruderu.arttracker.controller.media.tv;

import com.ruderu.arttracker.entity.tv.Tv;
import com.ruderu.arttracker.repo.tv.TvRepo;
import com.ruderu.arttracker.util.DateFormat;
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
public class AddTvController {

    @Autowired
    private TvRepo tvRepo;

    @GetMapping("title/tv/add")
    public String get(
            Model model
    ) {
        return "media/tv/add";
    }

    @PostMapping("title/tv/add")
    public String post(
            @ModelAttribute("tv") Tv tv,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate
    ) {
        if(startDate != null) {
            tv.setStartDate(startDate);
        }
        if(endDate != null) {
            tv.setEndDate(endDate);
        }

        tv.setId(null);
        System.out.println(tv);
        tvRepo.save(tv);

        return "media/tv/add";
    }
}
