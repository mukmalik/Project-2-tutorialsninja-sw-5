package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.AccountRegisterPage;
import com.tutorialsninja.demo.pages.HomePage;
import com.tutorialsninja.demo.pages.MyAccountPage;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

@Listeners(com.tutorialsninja.demo.customlisteners.CustomListeners.class)
public class AccountRegisterPageTest extends BaseTest {
    HomePage homePage;
    AccountRegisterPage accountRegisterPage;
    MyAccountPage accountPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt(){
        homePage = new HomePage();
        accountRegisterPage = new AccountRegisterPage();
        accountPage=new MyAccountPage();
    }
    @Test(groups = {"sanity", "smoke","regression"},dataProvider = "credentials", dataProviderClass = TestData.class)
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {

        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Register");
        Assert.assertEquals(accountRegisterPage.getRegisterAccountText(),
                "Register Account", "Register page not displayed");
    }

    @Test(groups = {"smoke","regression"},dataProvider = "credentials", dataProviderClass = TestData.class)
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Register");
        accountRegisterPage.enterFirstName("prime" + getAlphaNumericString(4));
        accountRegisterPage.enterLastName("test" + getAlphaNumericString(4));
        accountRegisterPage.enterEmail("prime" + getAlphaNumericString(4) + "@gmail.com");
        accountRegisterPage.enterTelephone("07988112233");
        accountRegisterPage.enterPassword("test123");
        accountRegisterPage.enterConfirmPassword("test123");
        accountRegisterPage.selectSubscription("Yes");
        accountRegisterPage.clickOnPrivacyPolicyCheckBox();
        accountRegisterPage.clickOnContinueButton();
        Assert.assertEquals(accountPage.getYourAccountHasBeenCreatedText(), "Your Account Has Been Created!",
                "Account not created");
        accountPage.clickOnContinueButton();
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Logout");
        Assert.assertEquals(accountPage.getAccountLogoutText(), "Account Logout", "Not logged out");
        accountPage.clickOnContinueButton();
    }
}