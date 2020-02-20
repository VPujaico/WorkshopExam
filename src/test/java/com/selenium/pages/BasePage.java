package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * This method returns the current url used
     * @return
     */
    public String getCurrentUrlUsedByUser(){
        return driver.getCurrentUrl();
    }

    /**
     * Method to do click on a WebElement
     * @param locator
     */
    public void clickOnElement(By locator){
        driver.findElement(locator).click();
    }

    /**
     * Method to do click on a WebElement
     * @param element
     */
    public void clickOnElement(WebElement element){
        element.click();
    }

    /**
     * Method to fill a WebElement with a text value
     * @param locator
     * @param text
     */
    public void sendKeysToElement(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }

     /**
     * This method returns the text value of a WebElement
     * @param locator
     * @return
     */
    public String getTextFromElement(By locator){
        return driver.findElement(locator).getText();
    }

    /**
     *This method simulate the action of move the cursor mouse over a WebElement
     * @param locator
     */
    public void simulateMouseOverAnElement(By locator){
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(locator);
        builder.moveToElement(element).build().perform();
    }

    /**
     * This method returns a list of WebElements
     * @param locator
     * @return
     */
    public List<WebElement> createAListOfWebElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * This method returns a list of text values of the WebElements of a group
     * @param locator
     * @return
     */
    public List<String> createAListWithElementTextValues(By locator) {
        List<WebElement> webElementList =  driver.findElements(locator);
        List<String> textValues =  new ArrayList<>();
        for (int i = 0; i < webElementList.size();  i++){
            textValues.add(webElementList.get(i).getText());
        }
        return textValues;
    }

    /**
     * This method returns a list of text values of the first N WebElements of a group
     * @param locator
     * @param N
     * @return
     */
    public List<String> createAListWithFirstNElementTextValues(By locator, Integer N) {
        List<WebElement> webElementList =  driver.findElements(locator);
        List<String> textValues =  new ArrayList<>();
        for (int i = 0; i < N;  i++){
            textValues.add(webElementList.get(i).getText());
        }
        return textValues;
    }

    /**
     * This method returns an ordered list in ascending mode
     * @param sortedList
     * @return
     */
    public List<String> sortTextListByAscending (List<String> sortedList){
        Collections.sort(sortedList);
        return sortedList;
    }
}