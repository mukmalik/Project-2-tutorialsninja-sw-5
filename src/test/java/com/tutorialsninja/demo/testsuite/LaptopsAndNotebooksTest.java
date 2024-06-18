package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.HomePage;
import com.tutorialsninja.demo.pages.LaptopsAndNotebooksPage;
import com.tutorialsninja.demo.pages.ProductPage;
import com.tutorialsninja.demo.pages.ShoppingCartPage;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(com.tutorialsninja.demo.customlisteners.CustomListeners.class)
public class LaptopsAndNotebooksTest extends BaseTest {
    HomePage homePage ;
    ProductPage productPage ;
    ShoppingCartPage cartPage;
    LaptopsAndNotebooksPage laptopsAndNotebooksPage ;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        productPage = new ProductPage();
        cartPage = new ShoppingCartPage();
        laptopsAndNotebooksPage = new LaptopsAndNotebooksPage();
        softAssert = new SoftAssert();
    }
    @Test(groups = {"sanity", "smoke","regression"})
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        homePage.selectCurrency("£Pound Sterling");
        homePage.mouseHoverOnLaptopsAndNotebooksLinkAndClick();
        homePage.selectMenu("Show AllLaptops & Notebooks");
        // Get all the products price and stored into array list
        List<Double> originalProductsPrice = laptopsAndNotebooksPage.getProductsPriceList();
        // Sort By Reverse order
        Collections.sort(originalProductsPrice, Collections.reverseOrder());
        // Select sort by Price (High > Low)
        laptopsAndNotebooksPage.selectSortByOption("Price (High > Low)");
        // After filter Price (High > Low) Get all the products name and stored into array list
        ArrayList<Double> afterSortByPrice = laptopsAndNotebooksPage.getProductsPriceList();
        Assert.assertEquals(originalProductsPrice, afterSortByPrice, "Product not sorted by price High to Low");
    }

    @Test(groups = { "smoke","regression"})
    public void verifyThatUserPlaceOrderSuccessfully() {
        homePage.selectCurrency("£Pound Sterling");
        homePage.mouseHoverOnLaptopsAndNotebooksLinkAndClick();
        homePage.selectMenu("Show AllLaptops & Notebooks");
        laptopsAndNotebooksPage.selectSortByOption("Price (High > Low)");
        laptopsAndNotebooksPage.selectProductByName("MacBook");
        Assert.assertEquals(productPage.getProductText(), "MacBook", "MacBook Product not display");
        productPage.clickOnAddToCartButton();
        Assert.assertTrue(productPage.getProductAddedSuccessMessage().contains("Success: You have added MacBook to your shopping cart!"),
                "Product not added to cart");
        productPage.clickOnShoppingCartLinkIntoMessage();
        Assert.assertTrue(cartPage.getShoppingCartText().contains("Shopping Cart"));
        Assert.assertEquals(cartPage.getProductName(), "MacBook", "Product name not matched");
        cartPage.changeQuantity("2");
        cartPage.clickOnQtyUpdateButton();
        Assert.assertTrue(cartPage.getSuccessModifiedMessage().contains("Success: You have modified your shopping cart!"));
        softAssert.assertEquals(cartPage.getTotal(), "£737.45", "Total not matched");
        softAssert.assertAll();
    }

}