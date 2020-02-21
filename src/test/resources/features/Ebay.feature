Feature: Search Functionality
  In order to search products in eBay WebSite.
  Users should be able to use some search
  functionalities of eBay WebSite.

  @test
  Scenario Outline: Search for an item in the eBay database through its website
    Given the user is in the eBay main web page
    When the user type the word "<productName>" in the search text box
    And the user click on the search button
    And the user click on the brand filter option choosing "<brandName>" brand
    And the user click on the size filter option choosing "<sizeProduct>" size
    And a quantity of "<productName>" is displayed as result
    And the user order all results by total price in ascendant mode
    Then verify that the first five products displayed are the cheapest
      |cheapestProducts |
      |Zapatos Puma De Alta Calidad  |
      |Puma Adultos Unisex leadcat Verano Playa Deportes Correa Diapositiva Sandalias Zapatos Negro.  |
      |Puma Golf para hombres Bunki Chanclas Sandalias Piscina Zapatos blanco flecos  |
      |Las diapositivas del gato Puma plomo (36026302) Deportes Sandalias Flip Flop Zapatos Zapatillas diapositiva  |
      |Las diapositivas del gato Puma plomo (36026301) Deportes Sandalias Flip Flop Zapatos Zapatillas diapositiva  |
    Examples:
      |productName |brandName |sizeProduct |
      |shoes  |PUMA |10 |