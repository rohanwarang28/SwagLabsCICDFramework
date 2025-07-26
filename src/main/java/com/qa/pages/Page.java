package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {

    WebDriver driver;
    WebDriverWait wait;

    public Page(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public abstract void type(By byLocator, String text);

    public abstract void click(By byLocator);

    public abstract WebElement getWebElement(By byLocator);

    public abstract void navigateToUrl(String url);

    public abstract Boolean isElementDisplayed(By byLocator);
}
