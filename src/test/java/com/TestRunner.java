package com;

import com.selenium.pages.MainPage;
import com.selenium.pages.SearchingPage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber_json_reports/home-page.json",
                "json:target/cucumber.json",
                "html:target/home-page-html"},
        features = {"src/test/resources/features"},
        glue = {"com.selenium.steps"},
        tags = "@test")

public class TestRunner extends AbstractTestNGCucumberTests {
    static private RemoteWebDriver webDriver;
    //Creating the objects of the pages
    protected static MainPage mainPage;
    protected static SearchingPage searchingPage;

    /**
     * This method initializes the browser depending on the value of the parameter
     * @param browser
     */
    @BeforeSuite
    @Parameters({"browser"})
    public void startBrowser (@Optional("chrome") String browser){
        switch (browser){
            case "chrome":
                startChrome();
                break;
        }
    }

    /**
     * This method initializes Chrome driver using io.github.bonigarcia libraries
     * @return RemoteWebDriver
     */
    public RemoteWebDriver startChrome(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        try {
            WebDriverManager.chromedriver().version("80.0").setup();
            webDriver = new ChromeDriver(options);
            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            //Initializing Pages
            mainPage = new MainPage(webDriver);
            searchingPage = new SearchingPage(webDriver);
        }catch (Exception e){
            System.out.println("Exception at moment to generate the Driver: " + e);
        }
        return webDriver;
    }

    @BeforeClass
    @Parameters({"url"})
    public void navigateTo (String url){
        webDriver.navigate().to(url);
    }

    @AfterSuite
    public void closeDriver(){
        webDriver.close();
    }
}