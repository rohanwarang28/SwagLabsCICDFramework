package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    WebDriver driver;
    By usernameTextbox = By.id("user-name");
    By passwordTextbox = By.id("password");
    By loginBtn = By.id("login-button");


    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public HomePage login(String username , String password){
        driver.findElement(usernameTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(loginBtn).click();
        return new HomePage(driver);
    }

}
