package com.ruderu.mediarecord.repo.book;

import com.ruderu.mediarecord.entity.book.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    List<Book> findByStatus(String status);
}
