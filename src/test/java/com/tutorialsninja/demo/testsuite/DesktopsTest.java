package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.DesktopPage;
import com.tutorialsninja.demo.pages.HomePage;
import com.tutorialsninja.demo.pages.ProductPage;
import com.tutorialsninja.demo.pages.ShoppingCartPage;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

import java.util.ArrayList;
import java.util.Collections;

@Listeners(com.tutorialsninja.demo.customlisteners.CustomListeners.class)
public class DesktopsTest extends BaseTest {
    HomePage homePage;
    DesktopPage desktopPage;
    ProductPage productPage;
    ShoppingCartPage cartPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        desktopPage = new DesktopPage();
        productPage = new ProductPage();
        cartPage = new ShoppingCartPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyProductArrangeInAlphabeticalOrder() {
        homePage.selectCurrency("£Pound Sterling");
        homePage.mouseHoverOnDesktopsLinkAndClick();
        homePage.selectMenu("Show AllDesktops");
        // Get all the products name and stored into array list
        ArrayList<String> originalProductsName = desktopPage.getProductsNameList();
        // Sort By Reverse order
        Collections.reverse(originalProductsName);
        // Select sort by Name Z - A
        desktopPage.selectSortByOption("Name (Z - A)");
        // After filter Z -A Get all the products name and stored into array list
        ArrayList<String> afterSortByZToAProductsName = desktopPage.getProductsNameList();
        Assert.assertEquals(originalProductsName, afterSortByZToAProductsName, "Product not sorted into Z to A order");
    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "dataset", dataProviderClass = TestData.class)
    public void verifyProductAddedToShoppingCartSuccessFully(String product, String qty, String successMessage, String productName, String model, String total){
        //  homePage.selectCurrency("£Pound Sterling");
        homePage.mouseHoverOnDesktopsLinkAndClick();
        homePage.selectMenu("Show AllDesktops");
        desktopPage.selectSortByOption("Name (A - Z)");
        desktopPage.allProduct(product);
        desktopPage.updateQuantity(qty);
        desktopPage.addToCart();

        String expectedText1 = successMessage;
        String actualText1 = desktopPage.getSuccessText();
        boolean message = actualText1.contains(expectedText1.trim());
        desktopPage.clickOnShoppingCart();

        String expectedMessage1 = productName;
        String actualMessage1 = desktopPage.verifyProductName(productName);
        Assert.assertEquals(actualMessage1, expectedMessage1, "Product Name not found");

        String expectedMessage2 = model;
        String actualMessage2 = desktopPage.verifyModel(model);
        Assert.assertEquals(actualMessage2, expectedMessage2, "Model not found");

        String expectedTotal = total;
        String actualTotal = desktopPage.verifyTotal();
        Assert.assertEquals(actualTotal, expectedTotal, "Error displayed");
    }

}