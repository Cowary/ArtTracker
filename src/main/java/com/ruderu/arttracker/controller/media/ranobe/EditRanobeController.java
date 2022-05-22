package com.ruderu.arttracker.controller.media.ranobe;

import com.ruderu.arttracker.entity.ranobe.Ranobe;
import com.ruderu.arttracker.repo.RanobeRep;
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

    @GetMapping("title/ranobe/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        Ranobe ranobe = ranobeRep.findById(id).orElseThrow();

        model.addAttribute("ranobe", ranobe);
        model.addAttribute("edit", true);

        return "media/ranobe/addRanobe";
    }

    @PostMapping("title/ranobe/edit")
    public String post(
            @ModelAttribute("ranobe") Ranobe ranobe
    ) {
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
