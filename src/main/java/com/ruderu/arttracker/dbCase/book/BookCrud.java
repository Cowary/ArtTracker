package com.ruderu.arttracker.dbCase.book;

import com.ruderu.arttracker.entity.book.Book;
import com.ruderu.arttracker.repo.book.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookCrud {

    @Autowired
    BookRepo bookRepo;

    public List<Book> getAll(String status) {
        if(status.equals("")) return bookRepo.findAll();
        else return bookRepo.findByStatus(status);
    }
}
