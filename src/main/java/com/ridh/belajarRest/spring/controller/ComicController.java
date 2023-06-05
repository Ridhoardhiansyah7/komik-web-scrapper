package com.ridh.belajarRest.spring.controller;


import com.google.gson.*;
import com.ridh.belajarRest.spring.model.ModelDetail;
import com.ridh.belajarRest.spring.model.ModelSearch;
import com.ridh.belajarRest.spring.servies.INewUpdate;
import com.ridh.belajarRest.spring.servies.IPopuler;
import com.ridh.belajarRest.utils.Endpoint;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ComicController implements IPopuler, INewUpdate {

    private final JsonObject jsonObject = new JsonObject();
    private final JsonObject rootJson = new JsonObject();
    private final Gson gson = new Gson();

    @Autowired
    private IPopuler iPopuler;

    @Autowired
    private INewUpdate iNewUpdate;

    @GetMapping("/")
    public String rootValue(){

        String ednpointListKomikPopuler = "/populer";
        String ednpointListKomikNewUpdate = "/update";
        String ednpointDetailKomik = "/detail?endpoint={endpoint}";
        String ednpointChapterKomik = "/chapter?endpoint={endpoint}";
        String ednpointSearchKomik = "/search?q={namaKomik}";

        final List<String> listDesc = new ArrayList<>();
        listDesc.add(0,"EndPoint yg tersedia : ");
        listDesc.add(1,ednpointListKomikPopuler);
        listDesc.add(2,ednpointListKomikNewUpdate);
        listDesc.add(3,ednpointDetailKomik);
        listDesc.add(4,ednpointChapterKomik);
        listDesc.add(5,ednpointSearchKomik);

        this.rootJson.add("list", gson.toJsonTree(listDesc));
        return this.rootJson.toString();
    }

    @GetMapping("/populer")
    public String getItems() throws IOException {
        return iPopuler.getItems();
    }

    @Override
    @GetMapping("/update")
    public String getItemsUpdate() throws IOException {
        return iNewUpdate.getItemsUpdate();
    }

    @GetMapping(value = "/detail")
    private String getEndpointDetail(@RequestParam("endpoint") String endpoint) throws IOException {

        String modelHomesJson = iPopuler.getItems();
        JsonObject jsonObject = JsonParser.parseString(modelHomesJson).getAsJsonObject();

        JsonArray komikPopulerArray = jsonObject.getAsJsonArray("data");

            for (JsonElement element : komikPopulerArray) {
                JsonObject komikPopuler = element.getAsJsonObject();
                String komikEndpoint = komikPopuler.get("endpoint").getAsString();

                if (komikEndpoint.equals(endpoint)) {

                    getDetail(endpoint);
                    return this.jsonObject.toString();
                }
            }

            modelHomesJson = iNewUpdate.getItemsUpdate();
            jsonObject = JsonParser.parseString(modelHomesJson).getAsJsonObject();

            komikPopulerArray = jsonObject.getAsJsonArray("data");

                for (JsonElement element : komikPopulerArray) {
                    JsonObject komikPopuler = element.getAsJsonObject();
                    String komikEndpoint = komikPopuler.get("endpoint").getAsString();

                    if (komikEndpoint.equals(endpoint)) {

                        getDetail(endpoint);
                        return this.jsonObject.toString();
                    }
                }
                return null;
    }

    @GetMapping(value = "/search")
    private String getSearchComic(@RequestParam("q") String query) throws IOException {

        Document doc = Jsoup.connect(Endpoint.BASE_WEST_MANGA+"?s="+query)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();
        Elements searchElemen  = doc.getElementsByClass("darkmode").select("#content").select(".wrapper").select(".bixbox").select(".listupd");

        List<ModelSearch> listSearchList = new ArrayList<>();
        for (Element listSearch : searchElemen.select(".bs").select(".bsx")){
            String endpointToDetail = listSearch.select("a").attr("href").replace("https://westmanga.info/", "");
            String titles = listSearch.select("a").select(".bigor").select(".tt").text();
            String images = listSearch.select("a").select(".limit").select("img").attr("src");

            ModelSearch modelSearch = new ModelSearch(endpointToDetail, titles, images);
            listSearchList.add(modelSearch);
            this.jsonObject.add("data", gson.toJsonTree(listSearchList));
        }
        return this.jsonObject.toString();
    }

    @GetMapping(value = "/chapter")
    private String getEndpointChapter(@RequestParam("endpoint") String endpoint){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ridh\\Downloads\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(Endpoint.BASE_WEST_MANGA+endpoint);

        /*
        System.out.println(driver.getPageSource());
        WebElement classReaderElement = driver.findElement(By.cssSelector(".postarea .entry-content.entry-content-single.maincontent"));
        WebElement readerrea = classReaderElement.findElement(By.id("readerarea"));
        System.out.println(readerrea);
        String tes = driver.findElement(By.cssSelector(".ts-main-image.lazy.loaded")).getAttribute("src");
        System.out.println(tes);

         */

        for (WebElement imageHeaderList : driver.findElements(By.cssSelector(".ts-main-image.lazy.loaded"))){
            String headerImages = imageHeaderList.getAttribute("src");

        }

        List<String> listimageHeader = new ArrayList<>();
        for (WebElement imagesList : driver.findElements(By.cssSelector(".ts-main-image.lazy"))){
            String image = imagesList.getAttribute("data-src");
            listimageHeader.add(image);
            jsonObject.add("imageHeader", gson.toJsonTree(listimageHeader));
        }
        driver.quit();
        return this.jsonObject.toString();
    }

    private void getDetail(String endpoint) throws IOException {

        Document doc = Jsoup.connect(Endpoint.BASE_WEST_MANGA+endpoint)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements elemenHeaderComic = doc.getElementsByClass("seriestucon");
        for (Element listHearderComic : elemenHeaderComic){

            String title = listHearderComic.select(".seriestuheader").select(".entry-title").text();
            String image = listHearderComic.select(".seriestucontl").select(".thumb").select("img").attr("src");
            String rating = listHearderComic.select(".seriestucontl").select(".rating.bixbox").select(".num").text();
            String sinopsisJp = listHearderComic.select(".seriestucontentr").select(".seriestuhead").select(".entry-content.entry-content-single > p").text();
            String status = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(1).text();
            String type = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(3).text();
            String author = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(5).text();
            String postedBy = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(7).text();
            String postedOn = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(9).text();
            String updatedOn = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(11).text();


            List<ModelDetail> listHeader = new ArrayList<>();
            ModelDetail modelDetail = new ModelDetail(title,image,type,author,status,rating,postedBy,postedOn,updatedOn,sinopsisJp);
            listHeader.add(modelDetail);
            this.jsonObject.add("data", gson.toJsonTree(listHeader));

            List<ModelDetail> listGenreDetail = new ArrayList<>();
            Elements elemenGenre = doc.getElementsByClass("seriestugenre").select("a");
            for (Element listGenre : elemenGenre){
                String genreEndpoints = listGenre.attr("href").replace("https://westmanga.info/", "");
                String genreNames = listGenre.text();

                ModelDetail modelDetail1 = new ModelDetail(genreEndpoints, genreNames);
                listGenreDetail.add(modelDetail1);
                this.jsonObject.add("genres", gson.toJsonTree(listGenreDetail));

            }

            List<ModelDetail> listChapterDetail = new ArrayList<>();
            Elements elemenChapter = doc.getElementsByClass("eplister").select(".chbox");
            for (Element listChapter : elemenChapter){
                String chapterEndpoint = listChapter.select("a").attr("href").replace("https://westmanga.info/", "");
                String chapterNumber = listChapter.select("a").select(".chapternum").text();
                String chapterDate = listChapter.select("a").select(".chapterdate").text();
                String downloadLink = listChapter.select(".dt").select("a").attr("href");


                ModelDetail modelDetail2 = new ModelDetail(chapterEndpoint, chapterNumber, chapterDate, downloadLink);
                listChapterDetail.add(modelDetail2);
                this.jsonObject.add("chapters", gson.toJsonTree(listChapterDetail));
            }
        }
    }
}
