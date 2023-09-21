package com.quoter.quoter.service.impl;

import com.quoter.quoter.dto.RangeDto;
import com.quoter.quoter.repository.BookRepository;
import com.quoter.quoter.service.BookService;
import com.quoter.quoter.service.WebScrapeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {

    @Value("${gutendex.endpoint}")
    private String gutenbergEndpoint ;//move it to the config file

    @Value("${gutenberg.plain.format}")
    private String bookPlainTextFormat;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    WebScrapeService webScrapeService;

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public String randomBook() throws IOException {//get the id, fetch the text, parse it according to the given indexes
        //gelen 500 hatası incelenip 500 hatası geldiğibnde tekrar istek atılacak şekilde düzenlenecek
        RestTemplate restTemplate = new RestTemplate();
        Random r = new Random();
        StringBuilder plainTextUrl = new StringBuilder();
        String gResponse = restTemplate.getForEntity(gutenbergEndpoint,String.class).getBody();
        int count = (int) getBookCount(gResponse);
        int randomGeneratedValue = r.nextInt(count);

        /*plainTextUrl.append(bookPlainTextFormat)
                .append(randomGeneratedValue)
                .append("/pg")
                .append(randomGeneratedValue)
                .append(".txt");*/

        return getPlainTextUrl(randomGeneratedValue);//getRandomChunkFromBook(Jsoup.connect(plainTextUrl.toString()).get().html());

    }
    private String getPlainTextUrl(int bookId){
        RestTemplate restTemplate = new RestTemplate();
        String gJson = restTemplate.getForEntity(gutenbergEndpoint+bookId,String.class).getBody();
        return gJson;
    }
    private String getRandomChunkFromBook(String html){
        Document document = Jsoup.parseBodyFragment(html);
        Element body = document.body();
        Elements text = body.getElementsByTag("body");
        for (Element simple : text){
            return simple.text();
        }
        return "";
    }

    private String getTextInRange(int startIndex,int endIndex){
        return "";
    }

    private RangeDto calculateRange(int characterCount){
        RangeDto range = new RangeDto();
        return range;
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

    private String getRandomChunk(String wholeText){

        return wholeText;
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
