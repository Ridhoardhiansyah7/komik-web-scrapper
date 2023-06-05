package com.ridh.belajarRest.jsoup;

import com.ridh.belajarRest.utils.Endpoint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class ChapterComicScrapper {

    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ridh\\Downloads\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(Endpoint.BASE_WEST_MANGA+"hajirau-kimi-ga-mitainda-chapter-36-bahasa-indonesia/");

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
            System.out.println(headerImages);
        }

        for (WebElement imagesList : driver.findElements(By.cssSelector(".ts-main-image.lazy"))){
            String image = imagesList.getAttribute("data-src");
            System.out.println(image);
        }
        driver.quit();
    }
}
