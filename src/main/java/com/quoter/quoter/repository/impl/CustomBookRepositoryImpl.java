package com.quoter.quoter.repository.impl;

import com.quoter.quoter.model.Book;
import com.quoter.quoter.repository.BookRepository;
import com.quoter.quoter.repository.CustomBookRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomBookRepositoryImpl implements CustomBookRepository {
    public List<Book> findByAuthor(String author){
        List<Book> books = new ArrayList<>();
        return books;
    }

}
