package com.ruderu.mediarecord.controller.media.anime;

import com.ruderu.mediarecord.dbCase.anime.AnimeRoleCrud;
import com.ruderu.mediarecord.dbCase.anime.AnimeStudioCrud;
import com.ruderu.mediarecord.entity.anime.Anime;
import com.ruderu.mediarecord.model.shiki.AnimeModel;
import com.ruderu.mediarecord.model.shiki.StudioModel;
import com.ruderu.mediarecord.repo.AnimeRepo;
import com.ruderu.mediarecord.repo.AnimeStudioRep;
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
import java.util.List;

@Controller
public class AddAnimeController {

    @Autowired
    AnimeRepo animeRepo;
    @Autowired
    AnimeStudioRep animeStudioRep;
    @Autowired
    AnimeStudioCrud animeStudioCrud;
    @Autowired
    AnimeRoleCrud animeRoleCrud;

    @GetMapping("title/anime/add")
    public String get(
            @RequestParam int animeId,
            Model model
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(animeId);
        FileUtil.downloadFile("https://dere.shikimori.one" + animeModel.getImage().getOriginal(), "image");

        Anime anime = new Anime(animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), DateFormat.HTMLshort.parse(animeModel.getAired_on()), (long) animeModel.getId());
        List<StudioModel> studioModelList = List.of(animeModel.getStudios());
        model.addAttribute("anime", anime);
        model.addAttribute("startDate", anime.getStartDate());
        model.addAttribute("endDate", anime.getEndDate());
        model.addAttribute("studioList", studioModelList);
        model.addAttribute("ongoingStart", "no");
        String url = "/resources/images/image.jpeg";
        model.addAttribute("image",url);
        System.out.println(DateFormat.HTML.format(anime.getReleaseDate()));

        return "media/anime/addAnim";
    }

    @PostMapping("title/anime/add")
    public String post(
            @ModelAttribute("anime") Anime anime,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam() String ongoingStart,
            @RequestParam(required = false) Integer episodesEnd
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(Math.toIntExact(anime.getShikiId()));
        anime.setDuration(animeModel.getDuration());

        List<StudioModel> studioModelList = List.of(animeModel.getStudios());
        if(startDate != null) {
            anime.setStartDate(startDate);
        } else {
            anime.setStartDate(DateUtil.def());
        }
        if(endDate != null) {
            anime.setEndDate(endDate);
        } else {
            anime.setEndDate(DateUtil.def());
        }
        if(ongoingStart != null) anime.setOngoingStart(ongoingStart);
        if(episodesEnd != null) anime.setEpisodesEnd(episodesEnd);

        animeRepo.save(anime);
        studioModelList.forEach(studioModel -> {
            animeStudioCrud.create(studioModel.getName(), anime.getId());
                }
        );
        animeRoleCrud.create(anime.getId(), animeModel.getRoleModels());
        File file = new File(FileUtil.path + "image" + ".jpeg");
        Assert.isTrue(file.delete(), "Rename file");
        return "media/anime/addAnim";
    }
}
