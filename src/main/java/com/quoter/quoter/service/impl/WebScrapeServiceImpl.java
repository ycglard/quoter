package com.quoter.quoter.service.impl;

import com.quoter.quoter.service.BookService;
import com.quoter.quoter.service.WebScrapeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebScrapeServiceImpl implements WebScrapeService {
    public static String baseUrlIsbn = "https://openlibrary.org/isbn/";

    @Override
    public String getSampleTextUrl(String isbn) {
        try {
            final Document doc = Jsoup.connect(baseUrlIsbn + isbn + ".json").get();
            final String fullTextUrl;

            for(Element row : doc.select("ul.ebook-download-options il")){
                System.out.println(row.data());
                //row.data();
            }

        }
        catch (Exception e){

        }
        return null;
    }
}
