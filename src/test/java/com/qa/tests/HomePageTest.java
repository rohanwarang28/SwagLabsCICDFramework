package com.qa.tests;

import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePageTest extends BaseTest{

    @Test
    public void verifySortedProductList() throws InterruptedException {
        LoginPage loginPage = new LoginPage(threadLocalDriver.get());
        HomePage homePage;
        SoftAssert softAssert = new SoftAssert();
        homePage = loginPage.login("standard_user","secret_sauce");
        List<String> actualProductNames = homePage
                .selectProductSortFilter("Name (A to Z)")
                .getProductsList();

        List<String> expectedProductNames = new ArrayList<String>(actualProductNames);
        Collections.sort(expectedProductNames);
        softAssert.assertEquals(actualProductNames,expectedProductNames.add("invalid"),"The product names are not in sorted order");

        softAssert.assertAll();
    }

}
