package com.tutorialsninja.demo.pages;

import com.tutorialsninja.demo.utilities.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends Utility {

    @CacheLookup
    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement shoppingCartText;
    @CacheLookup
    @FindBy(xpath ="//div[@class = 'table-responsive']/table/tbody/tr/td[2]/a" )
    WebElement productName;
    @CacheLookup
    @FindBy(xpath ="//div[@class = 'table-responsive']/table/tbody/tr/td[2]/small[1] ")
    WebElement deliveryDate ;
    @CacheLookup
    @FindBy(xpath ="//div[@class = 'table-responsive']/table/tbody/tr/td[3]" )
    WebElement model;
    @CacheLookup
    @FindBy(xpath="//div[@class = 'table-responsive']/table/tbody/tr/td[6]")
    WebElement total;;
    @CacheLookup
    @FindBy(xpath = "//input[contains(@name, 'quantity')]")
    WebElement qtyField;
    @CacheLookup
    @FindBy(xpath ="//button[contains(@data-original-title, 'Update')]" )

    WebElement qtyUpdateBtn;
    @CacheLookup
    @FindBy(xpath = "//div[@id='checkout-cart']/div[1]")
    WebElement successModifiedMessage;

    public String getShoppingCartText() {
        return getTextFromElement(shoppingCartText);
    }

    public String getProductName() {
        return getTextFromElement(productName);
    }

    public String getDeliveryDate() {
        return getTextFromElement(deliveryDate);
    }

    public String getModel() {
        return getTextFromElement(model);
    }

    public String getTotal() {
        return getTextFromElement(total);
    }

    public void changeQuantity(String qty) {
        sendTextToElement(qtyField, qty);
    }

    public void clickOnQtyUpdateButton() {
        clickOnElement(qtyUpdateBtn);
    }

    public String getSuccessModifiedMessage() {
        return getTextFromElement(successModifiedMessage);
    }
}