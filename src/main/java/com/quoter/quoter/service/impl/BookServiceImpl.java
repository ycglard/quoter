package com.quoter.quoter.service.impl;

import com.quoter.quoter.dto.ResultDto;
import com.quoter.quoter.model.Book;
import com.quoter.quoter.repository.BookRepository;
import com.quoter.quoter.service.BookService;
import com.quoter.quoter.service.WebScrapeService;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {

    static String gutenbergEndpoint = "https://gutendex.com/books";//move it to the config file

    static String bookPlainTextFormat = "https://www.gutenberg.org/cache/epub/";

    @Autowired
    BookRepository bookRepository;

    @Autowired
    WebScrapeService webScrapeService;

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public String randomBook() throws IOException {//get the id, fetch the text, parse it according to the given indexes
        RestTemplate restTemplate = new RestTemplate();
        Random r = new Random();
        StringBuilder plainTextUrl = new StringBuilder();
        String gResponse = restTemplate.getForEntity(gutenbergEndpoint,String.class).getBody();
        int count = (int) getBookCount(gResponse);
        int randomGeneratedValue = r.nextInt(count);

        plainTextUrl.append(bookPlainTextFormat)
                .append(randomGeneratedValue)
                .append("/pg")
                .append(randomGeneratedValue)
                .append(".txt");

        return Jsoup.connect(plainTextUrl.toString()).get().html();

    }
    //https://www.gutenberg.org/cache/epub/69640/pg69640-images.html
    //https://www.gutenberg.org/cache/epub/69638/pg69638-images.html
    //https://www.gutenberg.org/cache/epub/69638/pg69638.txt -> plain text
    //https://www.gutenberg.org/cache/epub/2643/pg2643.txt -> id'yi artırarak ilerleyince sonuç veriyor
    private Object getBookCount(String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Map<String, Object> map = mapper.readValue(json, Map.class);
            return map.get("count");
        }catch(Exception e){
            logger.error("error while parsing json:" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
    /*private Book mapJsonToBook(String json){
        Book book = new Book();

        ObjectMapper mapper = new ObjectMapper();
        try{
            Map<String, Object> map = mapper.readValue(json, Map.class);
        }catch(Exception e){
            logger.error("error while parsing json:" + Arrays.toString(e.getStackTrace()));
            return null;
        }

    }*/

}
