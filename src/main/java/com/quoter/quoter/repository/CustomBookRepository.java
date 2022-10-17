package com.quoter.quoter.repository;

import com.quoter.quoter.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomBookRepository {
    List<Book> findByAuthor(String author);

}
