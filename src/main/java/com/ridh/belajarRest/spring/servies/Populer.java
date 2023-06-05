package com.ridh.belajarRest.spring.servies;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ridh.belajarRest.spring.model.ModelHome;
import com.ridh.belajarRest.utils.Endpoint;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Populer implements IPopuler {
    private Document doc;
    private final JsonObject jsonObject = new JsonObject();
    private final Gson gson = new Gson();
    private final List<ModelHome> modelHomeList = new ArrayList<>();


    public String getItems() throws IOException{

        doc = Jsoup.connect(Endpoint.BASE_WEST_MANGA)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements populerElemen = doc.getElementsByClass("bixbox hothome full");
        String titlePopuler = populerElemen.select(".releases").select("h2").text().replace(" ", "_").replace("...", "").toLowerCase();
        Elements listPopuler = populerElemen.select(".listupd").select(".bs");
        for (Element list : listPopuler){

            String endpoint = list.select(".bsx").select("a").attr("href").replace("https://westmanga.info/", "");
            String image = list.select(".bsx").select(".limit").select("img").attr("src");
            String title =   list.select(".bsx").select(".bigor").select(".tt").text();
            String chapter = list.select(".bsx").select(".bigor").select(".adds").select(".epxs").text();
            String rating   = list.select(".bsx").select(".bigor").select(".adds").select(".rt").select(".rating").select(".numscore").text();

            ModelHome modelHome = new ModelHome(endpoint, title, image, chapter, rating);
            modelHomeList.add(modelHome);
            jsonObject.add("data", gson.toJsonTree(modelHomeList));

        }
        return jsonObject.toString();
    }

}
