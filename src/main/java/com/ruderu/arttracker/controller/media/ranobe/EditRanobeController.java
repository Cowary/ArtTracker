package com.ruderu.arttracker.controller.media.ranobe;

import com.ruderu.arttracker.entity.ranobe.Ranobe;
import com.ruderu.arttracker.repo.RanobeRep;
import com.ruderu.arttracker.util.DateFormat;
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
            @RequestParam long id,
            Model model
    ) {

        Ranobe ranobe = ranobeRep.findById(id).orElseThrow();

        model.addAttribute("ranobe", ranobe);

        return "media/ranobe/editRanobe";
    }

    @PostMapping("title/ranobe/edit")
    public String post(
            @RequestParam() long id,
            @RequestParam() String status,
            @RequestParam(required = false) String score,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam(required = false) String ongoingStart
    ) {
        Ranobe ranobe = ranobeRep.findById(id).orElseThrow();
        ranobe.setStatus(status);
        if(!score.isBlank()) {
            ranobe.setScore(Integer.valueOf(score));
        }
        else {
            ranobe.setScore(null);
        }
        if(startDate != null) {
            ranobe.setStartDate(startDate);
        }
        if(endDate != null) {
            ranobe.setEndDate(endDate);
        }
        if(ongoingStart != null) ranobe.setOngoingStart(ongoingStart);
        System.out.println(ranobe);
        ranobeRep.save(ranobe);
        return "redirect:../view/media";
    }

    @PostMapping("title/ranobe/delete")
    public String post(
            @RequestParam() long id
    ) {
        Ranobe ranobe = ranobeRep.findById(id).orElseThrow();
        ranobeRep.delete(ranobe);

        return "redirect:../view/media";
    }
}