package com.ridh.belajarRest.jsoup;

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

import java.io.IOException;
import java.util.List;

public class ManhuaComicScrapper {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(Endpoint.BASE_WEST_MANGA+"manga/?page=4&status=&type=manhua&order=")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements elements = doc.getElementsByClass("listupd").select(".bs");
        for (Element listManhwa : elements.select(".bsx")){

            String endpoint = listManhwa.select("a").attr("href").replace("https://westmanga.info/", "");
            String images = listManhwa.select(".limit").select("img").attr("src");
            String titles = listManhwa.select(".bigor").select(".tt").text();
            String chapters = listManhwa.select(".bigor").select(".epxs").text();
            String rating = listManhwa.select(".bigor").select(".rt").select(".numscore").text();

            System.out.println(endpoint);
            System.out.println(images);
            System.out.println(titles);
            System.out.println(chapters);
            System.out.println(rating);

        }

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ridh\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(Endpoint.BASE_WEST_MANGA+"manga/?page=4&status=&type=manhua&order=");

        List<WebElement> listPaginationElements = driver.findElements(By.cssSelector(".hpage a"));
        for (WebElement listPagination : listPaginationElements){

            String nextOrPrev = listPagination.getAttribute("href").replace("https://westmanga.info/", "");
            String textNextOrPrev = listPagination.getText();

            System.out.println(nextOrPrev);
            System.out.println(textNextOrPrev);
        }

        driver.quit();
    }
}
