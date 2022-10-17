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

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String title){//title'a ya da başka alanlara göre de getirilebilir
        List<Book> books = new ArrayList<>(bookRepository.findAll());

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            Book _book = bookRepository
                    .save(new Book(book.getName(), book.getDescription(),book.getContent()));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/books/line")
    public ResponseEntity<ResultDto> getRandomLine() {
        ResultDto resultDto = bookService.generateFinalProduct();
        if(Objects.isNull(resultDto))
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(resultDto,HttpStatus.OK);
    }
    @GetMapping("/books/book")
    public ResponseEntity<Book> getLineFromBook(@RequestParam String bookId) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/books/author")
    public ResponseEntity<Book> getLineFromAuthor(@RequestParam String authorId) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/books/year")
    public ResponseEntity<Book> getLineFromYear(@RequestParam String year) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
