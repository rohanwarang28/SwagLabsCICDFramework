package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage{

    By appHeaderLogo = By.xpath("//div[@class='app_logo' and text()='Swag Labs']");

    By productFilter = By.className("product_sort_container");

    By productNames = By.xpath("//div[@data-test='inventory-item-name']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public Boolean verifyAppHeaderLogoIsDisplayed(){
        return isElementDisplayed(appHeaderLogo);
    }

    public HomePage selectProductSortFilter(String value){
        selectValueFromDropdown(productFilter,value);
        return this;
    }

    public List<String> getProductsList(){
       return getWebElements(productNames).stream().map(s->s.getText()).collect(Collectors.toList());
    }
}
