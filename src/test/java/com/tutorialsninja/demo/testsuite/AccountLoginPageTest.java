package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.AccountLoginPage;
import com.tutorialsninja.demo.pages.HomePage;
import com.tutorialsninja.demo.pages.MyAccountPage;
import com.tutorialsninja.demo.propertyreader.PropertyReader;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

@Listeners(com.tutorialsninja.demo.customlisteners.CustomListeners.class)
public class AccountLoginPageTest extends BaseTest {
    HomePage homePage;
    AccountLoginPage accountLoginPage;
    MyAccountPage accountPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        accountLoginPage = new AccountLoginPage();
        accountPage = new MyAccountPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"}, dataProvider = "details", dataProviderClass = TestData.class)
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Login");
        Assert.assertEquals(accountLoginPage.getReturningCustomerText(),
                "Returning Customer", "Login page not displayed");
    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "details", dataProviderClass = TestData.class)
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Login");
        accountLoginPage.enterEmailAddress(PropertyReader.getInstance().getProperty("username"));
        accountLoginPage.enterPassword(PropertyReader.getInstance().getProperty("password"));
        accountLoginPage.clickOnLoginButton();
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Logout");
        Assert.assertEquals(accountPage.getAccountLogoutText(), "Account Logout", "Not logged out");
    }

}