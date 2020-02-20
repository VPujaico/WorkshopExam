package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver){
        super(driver);
    }

    By TBox_Search = By.id("gh-ac");
    By Btn_Search = By.id("gh-btn");

    /**
     * This method print in console the url website currently used
     */
    public void showTheUrlWebsiteNavigatedByUser(){
        System.out.println("The user is navigating in the following website: " + getCurrentUrlUsedByUser());
    }

    /**
     * This method send a text value to the search texBox
     * @param text_value
     */
    public void typeTheTextToSearchInSearchingTextBox(String text_value){
        sendKeysToElement(TBox_Search, text_value);
    }

    /**
     * This method click on the search button
     */
    public void clickOnSearchButton(){
        clickOnElement(Btn_Search);
    }
}