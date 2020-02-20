package com.selenium.pages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SearchingPage extends BasePage {
    public SearchingPage(WebDriver driver){
        super(driver);
    }

    By Cbx_Brands = By.cssSelector("[name='Brand']");
    By Cbx_Sizes = By.xpath("//li[@name='US%2520Shoe%2520Size%2520%2528Men%2527s%2529']//input");
    By Msg_Results = By.className("srp-controls__count-heading");
    By Drp_Order = By.cssSelector("[aria-controls='w9']");
    By Opt_SortAsc = By.cssSelector("[_sp='p2351460.m4116.l5869.c4']");
    By Name_Item = By.className("s-item__title");
    By Price_Item = By.className("s-item__price");
    By Ship_PItem = By.className("s-item__logisticsCost");

    /**
     * This method click on the brand filter option that match with the parameter
     * @param brand
     */
    public void clickOnABrandFilterOption(String brand){
        List<WebElement> ElementsList = createAListOfWebElements(Cbx_Brands);
        for (int i=0; i<ElementsList.size(); i++){
            if (ElementsList.get(i).getText().contains(brand)) {
                clickOnElement(ElementsList.get(i));
                break;
            }
        }
    }

    /**
     * This method click on the size filter option that match with the parameter
     * @param size
     */
    public void clickOnASizeFilterOption(String size){
        List<WebElement> ElementsList = createAListOfWebElements(Cbx_Sizes);
        for (int i=0; i<ElementsList.size(); i++){
            if (ElementsList.get(i).getAttribute("aria-label").contains(size)) {
                clickOnElement(ElementsList.get(i));
                break;
            }
        }
    }

    /**
     * This method take the text value of result label and print it in console if the product match with the parameter
     * @param product
     */
    public void getTheResultOfTheSearch(String product){
        if (getTextFromElement(Msg_Results).contains(product))
            System.out.println("5-\tThere are: " + getTextFromElement(Msg_Results) + "\n");
        else System.out.println("5-\tThe results are not about: " + product + "\n");
    }

    /**
     * This method display the list of sort criteria for the products
     */
    public void showTheListOfSortCriteria() {
        simulateMouseOverAnElement(Drp_Order);
    }

    /**
     * This method click on "Lowest First" option of Sort menu
     */
    public void clickOnLowestFirstSortCriteriaMenu(){
        clickOnElement(Opt_SortAsc);
    }

    /**
     * This method sort the products by price Ascending
     */
    public void sortTheProductsByPriceAscending() {
        showTheListOfSortCriteria();
        clickOnLowestFirstSortCriteriaMenu();
    }

    /**
     * This method take the first N products of a list to get their names
     * @param N
     * @return
     */
    public List<String> getNameOfFirstNProducts(int N){
        return createAListWithFirstNElementTextValues(Name_Item, N);
    }

    /**
     * This method take the first N products of a list to get their prices
     * @param N
     * @return
     */
    public List<String> getPriceOfFirstNProducts(int N){
        return createAListWithFirstNElementTextValues(Price_Item,N);
    }

    /**
     * This method take the first N products of a list to get their Shipping prices
     * @param N
     * @return
     */
    public List<String> getShippingPriceOfFirstNProducts(int N){
        return createAListWithFirstNElementTextValues(Ship_PItem, N);
    }

    /**
     * This method return True if my gherkin data table match with the first N product names after the ordering
     * @param listOfProducts
     * @return
     */
    public boolean verifyThatMyListOfProductsMatchWithTheFirstNProducts(DataTable listOfProducts) {
        List<String> productList = getNameOfFirstNProducts(listOfProducts.asList().size()-1);
        AtomicBoolean productsMatches = new AtomicBoolean(false);
        for (int i = 1; i < listOfProducts.asList().size(); i++ ){
            for (int f = 0; f < productList.size(); f++ ) {
                if (productList.get(f).contains(listOfProducts.asList().get(i))) {
                    productsMatches.set(true);
                    break;
                } else
                    productsMatches.set(false);
            }
            if (!productsMatches.get())
                break;
        }
        return productsMatches.get();
    }

    /**
     * This method print in console the first N products Ordered by price ascendant
     * @param N
     */
    public void printTheFirstNCheapProducts(int N) {
        System.out.print("\n8-\tTop " + N + " cheapest products: ");
        int j = 1;
        for (int i = 0; i < N; i++) {
            System.out.print("\n\t" + j + "° PRODUCT NAME:\t" + getNameOfFirstNProducts(N).get(i));
            System.out.print("\n\t" + "   PRODUCT PRICE:\t" + getPriceOfFirstNProducts(N).get(i));
            System.out.print("\n\t" + "   SHIPPING PRICE:\t" + getShippingPriceOfFirstNProducts(N).get(i));
            j++;
        }
        System.out.print("\n");
    }

    /**
     * This method print in console the first N products Ordered by name ascendant
     * @param N
     */
    public void printTheFirstNCheapProductsSortedByNameAsc(int N){
        System.out.print("\n9-\tTop " + N + " cheapest products sorted by name (Ascending): ");
        int j = 1;
        for (int i = 0; i < N;  i++) {
            System.out.print("\n\t" + j + "° PRODUCT NAME:\t" + sortTextListByAscending(getNameOfFirstNProducts(N)).get(i));
            j++;
        }
        System.out.print("\n");
    }

    /**
     * This method print in console the first N products Ordered by price descendant
     * @param N
     */
    public void printTheFirstNCheapProductsSortedByPriceDesc(int N) {
        System.out.print("\n10-\tTop " + N + " cheapest products sorted by price (Descending): ");
        int i = N;
        for (int j = 1; j <= N; j++) {
            i--;
            System.out.print("\n\t" + j + "° PRODUCT NAME:\t" + getNameOfFirstNProducts(N).get(i));
            System.out.print("\n\t" + "   PRODUCT PRICE:\t" + getPriceOfFirstNProducts(N).get(i));
            System.out.print("\n\t" + "   SHIPPING PRICE:\t" + getShippingPriceOfFirstNProducts(N).get(i));
        }
        System.out.print("\n");
    }
}