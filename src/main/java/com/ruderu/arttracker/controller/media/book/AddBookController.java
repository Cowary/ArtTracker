package com.ruderu.arttracker.controller.media.book;

import com.ruderu.arttracker.entity.book.Book;
import com.ruderu.arttracker.repo.book.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
            @ModelAttribute("book") Book book
    ) {
        bookRepo.save(book);

        return "media/book/add";
    }
}
