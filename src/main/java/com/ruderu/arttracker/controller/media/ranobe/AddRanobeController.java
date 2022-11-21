package com.ruderu.arttracker.controller.media.ranobe;

import com.ruderu.arttracker.dbCase.ranobe.RanobeCrud;
import com.ruderu.arttracker.dbCase.ranobe.RanobePublisherCrud;
import com.ruderu.arttracker.dbCase.ranobe.RanobeRoleCrud;
import com.ruderu.arttracker.dbCase.ranobe.RanobeVolumeCrud;
import com.ruderu.arttracker.entity.ranobe.Ranobe;
import com.ruderu.arttracker.entity.ranobe.RanobeVolume;
import com.ruderu.arttracker.integration.api.shiki.ShikimoriApi;
import com.ruderu.arttracker.integration.model.shiki.RanobeModel;
import com.ruderu.arttracker.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddRanobeController {

    @Autowired
    RanobeCrud ranobeCrud;
    @Autowired
    RanobePublisherCrud ranobePublisherCrud;
    @Autowired
    RanobeRoleCrud ranobeRoleCrud;
    @Autowired
    RanobeVolumeCrud ranobeVolumeCrud;

    @GetMapping("title/ranobe/add")
    public String get(
            @RequestParam(required = false) Integer ranobeId,
            Model model
    ) {
        if(ranobeId != null) {
            RanobeModel ranobeModel = ShikimoriApi.ranobeApi().getById(ranobeId);
            Ranobe ranobe = new Ranobe(ranobeModel.getName(), ranobeModel.getRussian(), ranobeModel.getVolumes(), ranobeModel.getChapters(), DateFormat.HTMLshort.parse(ranobeModel.getAired_on()), (long) ranobeModel.getId());
            Ranobe sqlRanobe = ranobeCrud.findByOriginalTitleAndUserId(ranobe.getOriginalTitle());
            if(sqlRanobe != null) ranobe = sqlRanobe;

            model.addAttribute("ranobe", ranobe);
            if(ranobe.getId() != null) model.addAttribute("ranobeId", ranobe.getId());
            String url = "https://dere.shikimori.one" + ranobeModel.getImage().getOriginal();
            model.addAttribute("add", true);
            model.addAttribute("image",url);
        }

        return "media/ranobe/addRanobe";
    }

    @PostMapping("title/ranobe/add")
    public String post(
            @ModelAttribute("ranobe") Ranobe ranobe,
            @RequestParam(value = "ranobeId", required = false) String ranobeId,
            @ModelAttribute("ranobeVolume") RanobeVolume ranobeVolume,
            @RequestParam("titleVolume") String titleVolume,
            RedirectAttributes redirectAttributes
    ) {
        RanobeModel ranobeModel = ShikimoriApi.ranobeApi().getById(Math.toIntExact(ranobe.getShikiId()));

        ranobeVolume.setTitle(titleVolume);
        ranobeVolume.setId(null);
        if(!ranobeId.isEmpty()) ranobe.setId(Long.valueOf(ranobeId));
        ranobe.setUsrId(ranobeVolume.getUsrId());
        ranobeCrud.save(ranobe);
        ranobePublisherCrud.create(ranobe.getId(), List.of(ranobeModel.getPublishers()));
        ranobeRoleCrud.createRanobeRole(ranobe.getId(), List.of(ranobeModel.getRoleModels()));
        ranobeVolumeCrud.save(ranobeVolume, ranobe);
        redirectAttributes.addAttribute("id", ranobeVolume.getId());

        return "redirect:../ranobe/edit";
    }
}
