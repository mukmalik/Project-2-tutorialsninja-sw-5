package com.tutorialsninja.demo.pages;

import com.tutorialsninja.demo.utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopPage extends Utility {
    @CacheLookup
    @FindBy(xpath = "//h2[contains(text(),'Desktops')]")

    WebElement desktopsText;
    @CacheLookup
    @FindBy(xpath = "//h4/a")
    WebElement productsList;

    @CacheLookup
    @FindBy(xpath = "input-sort")
    WebElement sortBy;

    @CacheLookup
    @FindBy(xpath = "//div[@class = 'input-group date']//button")
    WebElement calendarButton;

    @CacheLookup
    @FindBy(xpath = "//div[@class = 'datepicker']/div[1]//th[@class='picker-switch']")
    WebElement monthYear;

    @CacheLookup
    @FindBy(xpath = "//div[@id='top-links']//li[contains(@class,'open')]/ul/li")
    WebElement nextSign;

    @CacheLookup
    @FindBy(xpath = "//div[@class = 'datepicker']/div[1]//th[@class='next']")
    WebElement dates;

    @CacheLookup
    @FindBy(xpath = "//div[@class = 'table-responsive']/table/tbody/tr/td[6]")
    WebElement totalPrice;

    @CacheLookup
    @FindBy(css = "body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible")
    WebElement successMessage;

    @CacheLookup
    @FindBy(xpath = "//button[@id='button-cart']")
    WebElement addToCart;

    @CacheLookup
    @FindBy(xpath = "//input[@id='input-quantity']")
    WebElement quantity;

    @CacheLookup
    @FindBy(xpath = "//a[normalize-space()='shopping cart']")
    WebElement shoppingCart;


    public String getDesktopsText() {
        return getTextFromElement(desktopsText);
    }

    public ArrayList<String> getProductsNameList() {
        List<WebElement> products = getListOfElements(productsList);
        ArrayList<String> originalProductsName = new ArrayList<>();
        for (WebElement e : products) {
            originalProductsName.add(e.getText());
        }
        return originalProductsName;
    }

    public void selectProductByName(String product) {
        List<WebElement> products = getListOfElements(productsList);
        for (WebElement e : products) {
            if (e.getText().equals(product)) {
                e.click();
                break;
            }
        }
    }

    public void selectSortByOption(String option) {
        selectByVisibleTextFromDropDown((By) sortBy, option);
    }

    public void selectDate(String day, String month, String year) {
        clickOnElement(calendarButton);
        while (true) {
            String monAndYear = getTextFromElement(monthYear);
            String[] a = monAndYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(nextSign);
            }
        }
        List<WebElement> dateList = getListOfElements(dates);
        for (WebElement list : dateList) {
            if (list.getText().equalsIgnoreCase(day)) {
                list.click();
                break;
            }
        }
    }
    public ArrayList<String> getProductList() {
        List<WebElement> originalProductsName = driver.findElements(By.xpath("//h4/a"));
        ArrayList<String> originalProductsName1 = new ArrayList<>();
        for (WebElement e : originalProductsName) {
            originalProductsName1.add(e.getText());
        }
        Collections.sort(originalProductsName1);
        Collections.reverse(originalProductsName1);
        return originalProductsName1;
    }

    public ArrayList<String> getProductList1() {
        List<WebElement> afterFilterProductList = driver.findElements(By.xpath("//h4/a"));
        ArrayList<String> afterFilterProductList1 = new ArrayList<>();
        for (WebElement e : afterFilterProductList) {
            afterFilterProductList1.add(e.getText());
        }
        Collections.sort(afterFilterProductList1);
        Collections.reverse(afterFilterProductList1);
        return afterFilterProductList1;
    }
    public String getSuccessText() {
        return getTextFromElement(successMessage);
    }
    public void addToCart() {
        clickOnElement(addToCart);
    }
    public void updateQuantity(String value) {
        clearTextFromField(quantity,value);
    }
    public void allProduct(String product) {
        clickOnElement(By.xpath("//a[normalize-space()='" + product + "']"));
    }
    public void clickOnShoppingCart() {
        clickOnElement(shoppingCart);
    }
    public String verifyProductName(String productname) {
        return getTextFromElement(By.xpath("(//a[contains(text(),'" + productname + "')])[2]"));
    }

    public String verifyModel(String productmodel) {
        return getTextFromElement(By.xpath("//td[normalize-space()='" + productmodel + "']"));
    }

    public String verifyTotal() {
        return getTextFromElement(totalPrice);
    }
}