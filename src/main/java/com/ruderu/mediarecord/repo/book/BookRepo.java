package com.ruderu.mediarecord.repo.book;

import com.ruderu.mediarecord.entity.book.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long> {
}
