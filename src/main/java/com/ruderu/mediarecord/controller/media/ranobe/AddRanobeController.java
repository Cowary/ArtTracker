package com.ruderu.mediarecord.controller.media.ranobe;

import com.ruderu.mediarecord.entity.Ranobe;
import com.ruderu.mediarecord.model.shiki.RanobeModel;
import com.ruderu.mediarecord.repo.RanobeRep;
import com.ruderu.mediarecord.rest.ShikimoriApi;
import com.ruderu.mediarecord.util.DateFormat;
import com.ruderu.mediarecord.util.DateUtil;
import com.ruderu.mediarecord.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.Date;

@Controller
public class AddRanobeController {

    @Autowired
    RanobeRep ranobeRep;

    @GetMapping("title/ranobe/addRanobe")
    public String get(
            @RequestParam int ranobeId,
            Model model
    ) {
        File file = new File(FileUtil.path + "image" + ".jpeg");
        if(file.exists()) file.delete();
        RanobeModel ranobeModel = ShikimoriApi.findRanobeById(ranobeId);
        FileUtil.downloadFile("https://dere.shikimori.one" + ranobeModel.getImage().getOriginal(), "image");
        file = new File(FileUtil.path + "image" + ".jpeg");
        Assert.isTrue(file.exists(), "File");

        Ranobe ranobe = new Ranobe(ranobeModel.getName(), ranobeModel.getRussian(), ranobeModel.getChapters(), DateFormat.HTMLshort.parse(ranobeModel.getAired_on()), (long) ranobeModel.getId());
        //List<StudioModel> studioModelList = List.of(ranobeModel.getStudios());
        model.addAttribute("ranobe", ranobe);
        model.addAttribute("startDate", ranobe.getStartDate());
        model.addAttribute("endDate", ranobe.getEndDate());
        //model.addAttribute("studioList", studioModelList);
        model.addAttribute("ongoingStart", "no");
        String url = "/resources/images/image.jpeg";
        model.addAttribute("image",url);
        System.out.println(DateFormat.HTML.format(ranobe.getAiredOn()));

        return "media/ranobe/addRanobe";
    }

    @PostMapping("title/ranobe/addRanobe")
    public String post(
            @ModelAttribute("ranobe") Ranobe ranobe,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam() String ongoingStart
    ) {
        RanobeModel ranobeModel = ShikimoriApi.findRanobeById(Math.toIntExact(ranobe.getShikiId()));
        //ranobe.setDuration(animeModel.getDuration());

        //List<StudioModel> studioModelList = List.of(animeModel.getStudios());
        if(startDate != null) {
            ranobe.setStartDate(startDate);
        } else {
            ranobe.setStartDate(DateUtil.def());
        }
        if(endDate != null) {
            ranobe.setEndDate(endDate);
        } else {
            ranobe.setEndDate(DateUtil.def());
        }
        if(ongoingStart != null) ranobe.setOngoingStart(ongoingStart);

        ranobeRep.save(ranobe);
//        studioModelList.forEach(studioModel -> {
//                    animeStudioCrud.create(studioModel.getName(), ranobe.getId());
//                }
//        );
        File file = new File(FileUtil.path + "image" + ".jpeg");
        Assert.isTrue(file.delete(), "Rename file");
        return "media/ranobe/addRanobe";
    }
}
