package com.ruderu.arttracker.controller.list;

import com.ruderu.arttracker.dbCase.book.BookCrud;
import com.ruderu.arttracker.dbCase.manga.MangaCrud;
import com.ruderu.arttracker.dbCase.ranobe.RanobeCrud;
import com.ruderu.arttracker.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReadListController {

    @Autowired
    MangaCrud mangaCrud;
    @Autowired
    RanobeCrud ranobeCrud;
    @Autowired
    BookCrud bookCrud;

    @GetMapping("/title/view/read")
    public String get(@RequestParam(required = false, defaultValue = "") String status,
                      Model model) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(mangaCrud.getAll(status));
        mediaList.addAll(ranobeCrud.getAll(status));
        mediaList.addAll(bookCrud.getAll(status));

        mediaList = mediaList.stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .collect(Collectors.toList());

        model.addAttribute("mediaList", mediaList);
        return "media/view/read";
    }
}
