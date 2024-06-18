package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.ComponentsPage;
import com.tutorialsninja.demo.pages.DesktopPage;
import com.tutorialsninja.demo.pages.HomePage;
import com.tutorialsninja.demo.pages.LaptopsAndNotebooksPage;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.tutorialsninja.demo.customlisteners.CustomListeners.class)
public class TopMenuTest extends BaseTest {

    HomePage homePage;
    DesktopPage desktopPage;
    LaptopsAndNotebooksPage laptopsAndNotebooksPage;
    ComponentsPage componentsPage;
    @BeforeMethod(alwaysRun = true)
    public void inIt(){
        homePage = new HomePage();
        desktopPage = new DesktopPage();
        laptopsAndNotebooksPage=new LaptopsAndNotebooksPage();
        componentsPage=new ComponentsPage();
    }

    @Test(groups = {"sanity", "smoke","regression"})

    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        homePage.mouseHoverOnDesktopsLinkAndClick();
        homePage.selectMenu("Show AllDesktops");
        String expectedText = "Desktops";
        String actualText = desktopPage.getDesktopsText();
        Assert.assertEquals(actualText, expectedText, "Not navigate to Desktop page");
    }

    @Test(groups = { "smoke","regression"})

    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        homePage.mouseHoverOnLaptopsAndNotebooksLinkAndClick();
        homePage.selectMenu("Show AllLaptops & Notebooks");
        Assert.assertEquals(laptopsAndNotebooksPage.getLaptopsAndNotebooksText(),
                "Laptops & Notebooks", "Not navigate to Laptops and Notebooks page");
    }

    @Test(groups = {"regression"})

    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        homePage.mouseHoverOnComponentLinkAndClick();
        homePage.selectMenu("Show AllComponents");
        Assert.assertEquals(componentsPage.getComponentsText(),
                "Components", "Not navigate to Laptops and Notebooks page");
    }


}