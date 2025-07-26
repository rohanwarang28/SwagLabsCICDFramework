package com.qa.tests;

import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginPageTest extends BaseTest{

    @Test
    public void verifyLogin() throws InterruptedException {
        LoginPage loginPage;
        HomePage homePage;
        SoftAssert softAssert = new SoftAssert();
        loginPage = new LoginPage(threadLocalDriver.get());

        homePage = loginPage.login("standard_user","secret_sauce");

        boolean isLoginSuccessful = homePage.verifyAppHeaderLogoIsDisplayed();
        softAssert.assertTrue(isLoginSuccessful,"Login is not successful");
        softAssert.assertAll();

    }

}
