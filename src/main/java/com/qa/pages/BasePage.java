package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage extends Page {

    WebDriver driver;


    public BasePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    @Override
    public void type(By byLocator, String text) {
        getWebElement(byLocator).sendKeys(text);
    }

    @Override
    public void click(By byLocator) {
        getWebElement(byLocator).click();
    }

    @Override
    public WebElement getWebElement(By byLocator) {

        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        }
        catch (Exception e){

            System.out.println("Element not present");
            return null;
        }

    }

    @Override
    public void navigateToUrl(String url) {
       driver.get(url);
    }

    @Override
    public Boolean isElementDisplayed(By byLocator) {
        return getWebElement(byLocator).isDisplayed();
    }

    public void selectValueFromDropdown(By byLocator,String textToBeSelected){
        Select select = new Select(getWebElement(byLocator));
        select.selectByVisibleText(textToBeSelected);
    }

    public List<WebElement> getWebElements(By byLocator){
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
    }
}
