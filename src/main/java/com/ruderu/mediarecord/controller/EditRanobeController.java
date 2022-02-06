package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Ranobe;
import com.ruderu.mediarecord.repository.RanobeRep;
import com.ruderu.mediarecord.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class EditRanobeController {

    @Autowired
    RanobeRep ranobeRep;

    @GetMapping("title/ranobe/edit")
    public String get(
            @RequestParam long mangaId,
            Model model
    ) {

        Ranobe ranobe = ranobeRep.findById(mangaId).orElseThrow();

        model.addAttribute("ranobe", ranobe);

        return "editRanobe";
    }

    @PostMapping("title/ranobe/edit")
    public String post(
            @RequestParam() long ranobeId,
            @RequestParam() String status,
            @RequestParam() int score,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam() String ongoingStart
    ) {
        Ranobe ranobe = ranobeRep.findById(ranobeId).orElseThrow();
        ranobe.setStatus(status);
        ranobe.setScore(score);
        if(startDate != null) {
            ranobe.setStartDate(startDate);
        }
        if(endDate != null) {
            ranobe.setEndDate(endDate);
        }
        if(ongoingStart != null) ranobe.setOngoingStart(ongoingStart);
        System.out.println(ranobe);
        ranobeRep.save(ranobe);
        return "editRanobe";
    }

    @PostMapping("title/ranobe/delete")
    public String post(
            @RequestParam() long id
    ) {
        Ranobe ranobe = ranobeRep.findById(id).orElseThrow();
        ranobeRep.delete(ranobe);

        return "redirect:../ranobe";
    }
}
