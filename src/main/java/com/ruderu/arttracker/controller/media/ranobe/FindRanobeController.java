package com.ruderu.arttracker.controller.media.ranobe;

import com.ruderu.arttracker.entity.ranobe.Ranobe;
import com.ruderu.arttracker.rest.api.shiki.ShikimoriApi;
import com.ruderu.arttracker.rest.model.shiki.RanobeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FindRanobeController {

    @GetMapping("/title/ranobe/find")
    public String get() {
        return "media/ranobe/findRanobe";
    }

    @PostMapping("/title/ranobe/find")
    public String post(
            @ModelAttribute("ranobe") Ranobe ranobe,
            Model model
    ) {
        List<RanobeModel> list = ShikimoriApi.ranobeApi().searchByName(ranobe.getOriginalTitle());
        model.addAttribute("list", list);

        return "media/ranobe/findRanobe";

    }

    @PostMapping("/title/ranobe/save")
    public String post(
            @RequestParam int ranobeId,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addAttribute("ranobeId", ranobeId);

        return "redirect:./add";
    }
}
