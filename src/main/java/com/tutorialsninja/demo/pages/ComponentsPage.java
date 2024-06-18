package com.tutorialsninja.demo.pages;

import com.tutorialsninja.demo.utilities.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ComponentsPage extends Utility {
    @CacheLookup
    @FindBy(xpath ="//h2[contains(text(),'Components')]")
    WebElement componentsText;
    public String getComponentsText() {
        return getTextFromElement(componentsText);
    }
}
