package com.ruderu.arttracker.controller.media.tv;

import com.ruderu.arttracker.integration.api.kin.KinApi;
import com.ruderu.arttracker.integration.model.kin.KinResultModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FindTvController {

    @GetMapping("/title/tv/find")
    public String get(

    ) {
        return "media/tv/find";
    }

    @PostMapping("/title/tv/find")
    public String post(
            @RequestParam String keyword,
            Model model
    ) {
        List<KinResultModel> list = KinApi.serialApi().searchByKeyword(keyword);
        model.addAttribute("list", list);

        return "media/tv/find";

    }

    @PostMapping("/title/tv/save")
    public String post(
            @RequestParam int filmId,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addAttribute("filmId", filmId);

        return "redirect:./add";
    }
}
