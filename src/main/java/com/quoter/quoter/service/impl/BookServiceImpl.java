package com.quoter.quoter.service.impl;

import com.quoter.quoter.dto.ResultDto;
import com.quoter.quoter.model.Book;
import com.quoter.quoter.repository.BookRepository;
import com.quoter.quoter.service.BookService;
import com.quoter.quoter.service.WebScrapeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {

    static String gutenbergEndpoint = "https://gutendex.com/";

    @Autowired
    BookRepository bookRepository;

    @Autowired
    WebScrapeService webScrapeService;

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public String randomBook() {
        RestTemplate restTemplate = new RestTemplate();
        Random r = new Random();
        String gRequest = gutenbergEndpoint + "books";
        String gResponse = restTemplate.getForEntity(gRequest,String.class).getBody();
        int count = (int) getBookCount(gResponse);

        String gRandomResponse = restTemplate.getForEntity(gutenbergEndpoint + "books?ids=" + r.nextInt(count),String.class).getBody();
        return gRandomResponse;
    }

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
