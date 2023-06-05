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
public class NewUpdate implements INewUpdate{
    private final List<ModelHome> modelHomeList = new ArrayList<>();
    private final Gson gson = new Gson();
    private Document doc;
    private final JsonObject jsonObject = new JsonObject();
    @Override
    public String getItemsUpdate() throws IOException {

        doc = Jsoup.connect(Endpoint.BASE_WEST_MANGA)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements updateElemen = doc.getElementsByClass("postbody").select(".bixbox").select(".listupd").select(".bsx");
        String titleRelease = doc.getElementsByClass("postbody").select(".bixbox").get(1).select(".releases").select("h2").text().replace("LAINYA...", "").replace(" ", "_").toLowerCase();

        for (Element listUpdate : updateElemen){

            String endpointUpdate = listUpdate.select("a").attr("href").replace("https://westmanga.info/", "");
            String imageUpdate = listUpdate.select("a").select(".limit").select("img").attr("src");
            String titleUpdate = listUpdate.select(".bigor").select(".tt").select("a").text();
            String chapterUpdate  = listUpdate.select(".bigor").select(".adds").select("a").select(".epxs").text();
            String updateAt  = listUpdate.select(".bigor").select(".adds").select(".epxdate").text();

            ModelHome modelHome1 = new ModelHome(endpointUpdate, titleUpdate, imageUpdate ,chapterUpdate, updateAt);
            modelHomeList.add(modelHome1);
            jsonObject.add("data", gson.toJsonTree(modelHomeList));

        }
        return jsonObject.toString();
    }
}