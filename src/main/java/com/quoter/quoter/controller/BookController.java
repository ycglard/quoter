package com.quoter.quoter.controller;

import com.quoter.quoter.dto.ResultDto;
import com.quoter.quoter.model.Book;
import com.quoter.quoter.repository.BookRepository;
import com.quoter.quoter.repository.CustomBookRepository;
import com.quoter.quoter.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<String> getBook(){//title'a ya da başka alanlara göre de getirilebilir
        Book book = new Book();

        String books = bookService.randomBook();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
