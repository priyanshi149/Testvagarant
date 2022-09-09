package dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class movie {
    WebDriver driver = new ChromeDriver();
    public static String releaseDatewiki=null;
    public static String releaseDateimdb=null;
    ConfigFileReader configFileReader;

    @BeforeTest
    public void loadproperty(){
        ConfigFileReader obj= new ConfigFileReader();


    }

    @BeforeSuite
    public void search() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        driver.manage().window().maximize();
//        driver.get("https://www.google.com");
        configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", configFileReader.getmoviename());
    }

        @Test
        public void wiki () {
            driver.get("https://www.imdb.com");
            WebElement p = driver.findElement(By.xpath("//input[@placeholder='Search IMDb']"));
            String a=  System.getProperty("movieName");
              p.click();
              p.sendKeys(a);
              driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            WebElement r = driver.findElement(By.xpath("//ul[@role='listbox']//li[1]//a//div[2]//div[1]"));
            System.out.println(r.getText());
            r.click();
            releaseDateimdb = driver.findElement(By.xpath("(//a[contains(text(),'Release date')]//parent::li//parent::ul//parent::div//following-sibling::div//a)[1]")).getText();
            String countryimdb = driver.findElement(By.xpath("(//a[contains(text(),'Release date')]//parent::li//parent::ul//parent::div//following-sibling::div//a)[2]")).getText();
            System.out.println(releaseDateimdb);
            System.out.println(countryimdb);
            driver.get("https://www.wikipedia.com");
            WebElement q = driver.findElement(By.xpath("//input[@name='search']"));
            q.click();
            q.sendKeys(a);
            q.submit();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement s = driver.findElement(By.xpath("//ul[@class='mw-search-results']//li[1]//div//a"));
            System.out.println(s.getText());
            s.click();
            releaseDatewiki = driver.findElement(By.xpath("//div[contains(text(),'Release date')]//parent::th//parent::tr//following-sibling::td//li")).getText();
            String countrywiki = driver.findElement(By.xpath("//th[contains(text(),'Country')]//parent::tr//td")).getText();
            System.out.println(releaseDatewiki);
            System.out.println(countrywiki);
            driver.quit();
            Assert.assertEquals(releaseDatewiki, releaseDateimdb);
            Assert.assertEquals(countrywiki,countryimdb);

        }


    }


