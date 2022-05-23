package com.ruderu.arttracker.controller.media.book;

import com.ruderu.arttracker.dbCase.book.BookCrud;
import com.ruderu.arttracker.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditBookController {

    @Autowired
    BookCrud bookCrud;

    @GetMapping("title/book/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        Book book = bookCrud.findById(id);

        model.addAttribute("book", book);
        model.addAttribute("edit", true);

        return "media/book/add";
    }

    @PostMapping("title/book/edit")
    public String post(
            @ModelAttribute("book") Book book
    ) {
        bookCrud.save(book);

        return "redirect:../view/media";
    }

    @PostMapping("title/book/delete")
    public String post(
            @RequestParam() long id
    ) {
        Book book = bookCrud.findById(id);
        bookCrud.delete(book);

        return "redirect:../view/media";
    }
}
