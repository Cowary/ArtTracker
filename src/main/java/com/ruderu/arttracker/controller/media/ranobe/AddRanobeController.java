package com.ruderu.arttracker.controller.media.ranobe;

import com.ruderu.arttracker.dbCase.ranobe.RanobePublisherCrud;
import com.ruderu.arttracker.dbCase.ranobe.RanobeRoleCrud;
import com.ruderu.arttracker.entity.ranobe.Ranobe;
import com.ruderu.arttracker.model.shiki.RanobeModel;
import com.ruderu.arttracker.repo.RanobeRep;
import com.ruderu.arttracker.rest.ShikimoriApi;
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
import java.util.List;

@Controller
public class AddRanobeController {

    @Autowired
    RanobeRep ranobeRep;
    @Autowired
    RanobePublisherCrud ranobePublisherCrud;
    @Autowired
    RanobeRoleCrud ranobeRoleCrud;

    @GetMapping("title/ranobe/addRanobe")
    public String get(
            @RequestParam int ranobeId,
            Model model
    ) {
        RanobeModel ranobeModel = ShikimoriApi.findRanobeById(ranobeId);

        Ranobe ranobe = new Ranobe(ranobeModel.getName(), ranobeModel.getRussian(), ranobeModel.getVolumes(), ranobeModel.getChapters(), DateFormat.HTMLshort.parse(ranobeModel.getAired_on()), (long) ranobeModel.getId());
        model.addAttribute("ranobe", ranobe);
        String url = "https://dere.shikimori.one" + ranobeModel.getImage().getOriginal();
        model.addAttribute("add", true);
        model.addAttribute("image",url);
        System.out.println(DateFormat.HTML.format(ranobe.getReleaseDate()));

        return "media/ranobe/addRanobe";
    }

    @PostMapping("title/ranobe/addRanobe")
    public String post(
            @ModelAttribute("ranobe") Ranobe ranobe,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam(required = false) String comment,
            @RequestParam() String ongoingStart
    ) {
        RanobeModel ranobeModel = ShikimoriApi.findRanobeById(Math.toIntExact(ranobe.getShikiId()));

        if(startDate != null) {
            ranobe.setStartDate(startDate);
        }
        if(endDate != null) {
            ranobe.setEndDate(endDate);
        }
        if(ongoingStart != null) ranobe.setOngoingStart(ongoingStart);

        ranobeRep.save(ranobe);
        ranobePublisherCrud.create(ranobe.getId(), List.of(ranobeModel.getPublishers()));
        ranobeRoleCrud.createRanobeRole(ranobe.getId(), List.of(ranobeModel.getRoleModels()));

        return "media/ranobe/addRanobe";
    }
}
