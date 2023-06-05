package com.ridh.belajarRest.jsoup;

import com.ridh.belajarRest.utils.Endpoint;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SearchComicScrapper {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(Endpoint.BASE_WEST_MANGA+"?s=megami")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements searchElemen  = doc.getElementsByClass("darkmode").select("#content").select(".wrapper").select(".bixbox").select(".listupd");

        for (Element listSearch : searchElemen.select(".bs").select(".bsx")){
            String endpointToDetail = listSearch.select("a").attr("href");
            String titles = listSearch.select("a").select(".bigor").select(".tt").text();
            String images = listSearch.select("a").select(".limit").select("img").attr("src");

            System.out.println(endpointToDetail);
            System.out.println(titles);
            System.out.println(images);

        }

    }
}
