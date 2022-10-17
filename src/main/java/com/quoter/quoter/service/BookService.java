package com.quoter.quoter.service;

import com.quoter.quoter.dto.ResultDto;
import com.quoter.quoter.model.Book;
import org.springframework.stereotype.Service;


public interface BookService {
    Book randomBook();

    ResultDto generateFinalProduct();

    void fetchBookFromOL();

    String generateRandomISBN();

    String text(){}


}
