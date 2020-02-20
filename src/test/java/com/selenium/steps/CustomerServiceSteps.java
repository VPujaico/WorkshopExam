package com.selenium.steps;

import com.TestRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CustomerServiceSteps extends TestRunner {
    @Given("the user is in the eBay main web page")
    public void theUserIsInTheEBayMainWebPage() {
        mainPage.showTheUrlWebsiteNavigatedByUser();
    }

    @When("the user type the word {string} in the search text box")
    public void theUserTypeTheWordInTheSearchTextBox(String productName) {
        mainPage.typeTheTextToSearchInSearchingTextBox(productName);
    }

    @And("the user click on the search button")
    public void theUserClickOnTheSearchButton() {
        mainPage.clickOnSearchButton();
    }

    @And("the user click on the brand filter option choosing {string} brand")
    public void theUserClickOnTheBrandFilterOptionChoosingBrand(String brandName) {
        searchingPage.clickOnABrandFilterOption(brandName);
    }

    @And("the user click on the size filter option choosing {string} size")
    public void theUserClickOnTheSizeFilterOptionChoosingSize(String sizeProduct) {
        searchingPage.clickOnASizeFilterOption(sizeProduct);
    }

    @And("a quantity of {string} is displayed as result")
    public void aQuantityOfIsDisplayedAsResult(String product) {
        searchingPage.getTheResultOfTheSearch(product);
    }

    @And("the user order all results by total price in ascendant mode")
    public void theUserOrderAllResultsByTotalPriceInAscendantMode() {
        searchingPage.sortTheProductsByPriceAscending();
    }

    @Then("verify that the first five products displayed are the cheapest")
    public void verifyThatTheFirstFiveProductsDisplayedAreTheCheapest(DataTable cheapestProducts){
        Assert.assertTrue(searchingPage.verifyThatMyListOfProductsMatchWithTheFirstNProducts(cheapestProducts), "the order has been validated");
        System.out.println("\nThe list of cheapest products match with the first " + (cheapestProducts.asList().size() - 1) + " product results");
    }

    @After
    public void printInTheConsole(){
        int numberOfProducts = 5;
        searchingPage.printTheFirstNCheapProducts(numberOfProducts);
        searchingPage.printTheFirstNCheapProductsSortedByNameAsc(numberOfProducts);
        searchingPage.printTheFirstNCheapProductsSortedByPriceDesc(numberOfProducts);
    }
}