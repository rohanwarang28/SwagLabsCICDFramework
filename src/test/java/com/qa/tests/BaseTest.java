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


    @Parameters({"browserName"})
    @BeforeMethod
    public void setup(@Optional String browserName) throws IOException {
        if(browserName==null) {
            browser = AppConstants.browsername;
        }
        else {
            browser = browserName;
        }

        if(browser.equalsIgnoreCase("chrome")){
            if(AppConstants.platform.equalsIgnoreCase("local")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-save-password-bubble");
                threadLocalDriver.set(new ChromeDriver(chromeOptions));

                wait = new WebDriverWait(threadLocalDriver.get(), Duration.ofSeconds(30));
            }
        }
        else if(browser.equalsIgnoreCase("firefox")){
            if(AppConstants.platform.equalsIgnoreCase(("local"))) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                threadLocalDriver.set(new FirefoxDriver(firefoxOptions));
                wait = new WebDriverWait(threadLocalDriver.get(), Duration.ofSeconds(30));
            }
        }
        else{
            System.out.println("browser name is not valid");
        }
        threadLocalDriver.get().get(Utilities.getDataConfig("appUrl"));

    }

    @AfterMethod
    public void tearDown(){
        threadLocalDriver.get().quit();

    }

}
