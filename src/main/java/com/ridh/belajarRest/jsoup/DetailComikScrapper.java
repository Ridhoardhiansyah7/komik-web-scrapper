package com.ridh.belajarRest.jsoup;

import com.ridh.belajarRest.utils.Endpoint;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetailComikScrapper {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(Endpoint.BASE_WEST_MANGA + "manga/yumeochi-dreaming-of-falling-for-you/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements elemenHeaderComic = doc.getElementsByClass("seriestucon");
        for (Element listHearderComic : elemenHeaderComic){

            String title = listHearderComic.select(".seriestuheader").select(".entry-title").text();
            String image = listHearderComic.select(".seriestucontl").select(".thumb").select("img").attr("src");
            String rating = listHearderComic.select(".seriestucontl").select(".rating.bixbox").select(".num").text();
            String  sinopsisJp = listHearderComic.select(".seriestucontentr").select(".seriestuhead").select(".entry-content.entry-content-single > p").text();
            String status = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(1).text();
            String type = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(3).text();
            String author = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(5).text();
            String postedBy = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(7).text();
            String postedOn = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(9).text();
            String updatedOn = listHearderComic.select(".seriestucont").select(".infotable").select("tbody").select("tr").select("td").get(11).text();

            System.out.println(title);
            System.out.println(image);
            System.out.println(sinopsisJp);
            System.out.println(rating);
            System.out.println(status);
            System.out.println(type);
            System.out.println(author);
            System.out.println(postedBy);
            System.out.println(postedOn);
            System.out.println(updatedOn);

            Elements elemenGenre = doc.getElementsByClass("seriestugenre").select("a");
            for (Element listGenre : elemenGenre){
                String genreEndpoints = listGenre.attr("href").replace("https://westmanga.info/", "");
                String genreNames = listGenre.text();
                System.out.println(genreEndpoints);
                System.out.println(genreNames);
            }

            Elements elemenChapter = doc.getElementsByClass("eplister").select(".chbox");
            for (Element listChapter : elemenChapter){
                String chapterEndpoint = listChapter.select("a").attr("href").replace("https://westmanga.info/", "");
                String chapterNumber = listChapter.select("a").select(".chapternum").text();
                String chapterDate = listChapter.select("a").select(".chapterdate").text();
                String downloadLink = listChapter.select(".dt").select("a").attr("href");


                System.out.println(chapterEndpoint);
                System.out.println(chapterNumber);
                System.out.println(chapterDate);
                System.out.println(downloadLink);
            }
        }


    }

}
