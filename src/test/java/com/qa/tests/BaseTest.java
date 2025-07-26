package com.qa.tests;

import Base.AppConstants;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportHelper;
import utils.Utilities;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {


    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    protected String browser;

    protected WebDriverWait wait;
    private static final ExtentReports reports = ExtentReportHelper.getReportObject();
    ChromeOptions chromeOptions = new ChromeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    @Parameters({"browserName"})
    @BeforeMethod
    public void setup(@Optional String browserName) throws IOException {
        if(browserName==null) {
            browser = AppConstants.browsername;
        }
        else {
            browser = browserName;
        }

        if(browser.equalsIgnoreCase("chrome")) {
            if (AppConstants.platform.equalsIgnoreCase("local")) {

                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-save-password-bubble");
                threadLocalDriver.set(new ChromeDriver(chromeOptions));


            } else if (AppConstants.platform.equalsIgnoreCase("remote_git")) {
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                threadLocalDriver.set(new ChromeDriver(chromeOptions));
            }
        }
        else if(browser.equalsIgnoreCase("firefox")){
            if(AppConstants.platform.equalsIgnoreCase(("local"))) {

                firefoxOptions.addArguments("--disable-notifications");
                threadLocalDriver.set(new FirefoxDriver(firefoxOptions));

            }
            else if (AppConstants.platform.equalsIgnoreCase("remote_git"))
            {
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--no-sandbox");
                threadLocalDriver.set(new FirefoxDriver(firefoxOptions));
            }
        }
        else{
            System.out.println("browser name is not valid");
        }
        wait = new WebDriverWait(threadLocalDriver.get(), Duration.ofSeconds(30));
        threadLocalDriver.get().get(Utilities.getDataConfig("appUrl"));

    }

    @AfterMethod
    public void tearDown(){
        threadLocalDriver.get().quit();

    }

}
