package com.quoter.quoter.service;

import com.quoter.quoter.dto.ResultDto;
import com.quoter.quoter.model.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface BookService {
    String randomBook() throws IOException;


}
