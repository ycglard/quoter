package com.quoter.quoter.dto;

import com.quoter.quoter.model.Book;

//This class will expand in time, due to the change of expectations regarding to the modifications on result
public class ResultDto {
    private String line;
    private Book book;

    public ResultDto(){

    }
    public ResultDto(String line, Book book){
        this.line = line;
        this.book = book;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
