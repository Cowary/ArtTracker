package com.ruderu.arttracker.controller.media.book;

import com.ruderu.arttracker.entity.book.Book;
import com.ruderu.arttracker.repo.book.BookRepo;
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

@Controller
public class AddBookController {

    @Autowired
    BookRepo bookRepo;

    @GetMapping("title/book/add")
    public String get(
            Model model
    ) {
        return "media/book/add";
    }

    @PostMapping("title/book/add")
    public String post(
            @ModelAttribute("book") Book book,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate
    ) {
        if(startDate != null) {
            book.setStartDate(startDate);
        }
        if(endDate != null) {
            book.setEndDate(endDate);
        }
        System.out.println(book);
        bookRepo.save(book);

        return "media/book/add";
    }
}
