package com.ridh.belajarRest.jsoup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ridh.belajarRest.spring.model.ModelHome;
import com.ridh.belajarRest.utils.Endpoint;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HomeTreadingScraper {

    private static Document doc;

    public static void main(String[] args) throws IOException {

        doc = Jsoup.connect(Endpoint.BASE_WEST_MANGA)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        getPopulerComic();
        getNewUpdateComic();
    }

    public static void getPopulerComic(){

        System.out.println("===== POPULER =====");
        Elements populerElemen = doc.getElementsByClass("bixbox hothome full");
        String titleRelease = populerElemen.select(".releases").select("h2").text();
        System.out.println(titleRelease);

        Elements listPopuler = populerElemen.select(".listupd").select(".bs");
        for (Element list : listPopuler){

            String endpoint = list.select(".bsx").select("a").attr("href").replace("https://westmanga.info", "");
            String image = list.select(".bsx").select(".limit").select("img").attr("src");
            String title = list.select(".bsx").select(".bigor").select(".tt").text();
            String chapter = list.select(".bsx").select(".bigor").select(".adds").select(".epxs").text();
            String rating   = list.select(".bsx").select(".bigor").select(".adds").select(".rt").select(".rating").select(".numscore").text();

            JsonObject jsonObject = new JsonObject();
            ModelHome modelHome = new ModelHome(endpoint, title, image, chapter,rating);
            Gson gson = new Gson();

            jsonObject.add("komikPopuler", gson.toJsonTree(modelHome));
            System.out.println(jsonObject);

            System.out.println(endpoint);
            System.out.println(image);
            System.out.println(title);
            System.out.println(chapter);
            System.out.println(rating);
            System.out.println("=================");

        }
    }

    public static void getNewUpdateComic(){

        System.out.println("===== Newest =====");
        Elements updateElemen = doc.getElementsByClass("postbody").select(".bixbox").select(".listupd").select(".bsx");
        String titleRelease = doc.getElementsByClass("postbody").select(".bixbox").get(1).select(".releases").select("h2").text().replace("LAINYA...", "");
        System.out.println(titleRelease);


        for (Element listUpdate : updateElemen){

            String endpoint = listUpdate.select("a").attr("href").replace("https://westmanga.info", "");
            String image = listUpdate.select("a").select(".limit").select("img").attr("src");
            String title = listUpdate.select(".bigor").select(".tt").select("a").text();
            String chapter  = listUpdate.select(".bigor").select(".adds").select("a").select(".epxs").text();
            String updateAt  = listUpdate.select(".bigor").select(".adds").select(".epxdate").text();


            System.out.println(endpoint);
            System.out.println(title);
            System.out.println(image);
            System.out.println(chapter);
            System.out.println(updateAt);
            System.out.println("===================");


        }
    }
}
