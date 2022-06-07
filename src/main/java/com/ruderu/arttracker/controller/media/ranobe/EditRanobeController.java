package com.ruderu.arttracker.controller.media.ranobe;

import com.ruderu.arttracker.dbCase.ranobe.RanobeVolumeCrud;
import com.ruderu.arttracker.entity.ranobe.Ranobe;
import com.ruderu.arttracker.entity.ranobe.RanobeVolume;
import com.ruderu.arttracker.repo.ranobe.RanobeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditRanobeController {

    @Autowired
    RanobeRep ranobeRep;
    @Autowired
    RanobeVolumeCrud ranobeVolumeCrud;


    @GetMapping("title/ranobe/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        RanobeVolume ranobeVolume = ranobeVolumeCrud.getById(id);
        Ranobe ranobe = ranobeRep.findById(ranobeVolume.getRanobeId()).orElseThrow();

        model.addAttribute("ranobeVolume", ranobeVolume);
        model.addAttribute("titleVolume", ranobeVolume.getTitle());
        model.addAttribute("ranobe", ranobe);
        model.addAttribute("edit", true);

        return "media/ranobe/addRanobe";
    }

    @PostMapping("title/ranobe/edit")
    public String post(
            @ModelAttribute("ranobe") Ranobe ranobe,
            @ModelAttribute("ranobeVolume") RanobeVolume ranobeVolume,
            @RequestParam("titleVolume") String titleVolume
    ) {
        System.out.println(ranobe);
        //ranobe.setId(ranobeVolume.getRanobeId());
        ranobeVolume.setTitle(titleVolume);
        ranobeVolumeCrud.save(ranobeVolume, ranobe);
        return "redirect:../view/media";
    }

    @PostMapping("title/ranobe/delete")
    public String post(
            @RequestParam() long id
    ) {
        RanobeVolume ranobeVolume = ranobeVolumeCrud.getById(id);
        ranobeVolumeCrud.delete(ranobeVolume);

        return "redirect:../view/media";
    }
}
