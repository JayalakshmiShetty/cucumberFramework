Feature: Criptocurrencies


#@Test1
#  Scenario: Verify Criptocurrencies Count
#    Given I Launch CoinMarketCap
#    When I choose View 100 rows
#    Then Verify that 100 results are displayed
#
#  @Test2
#  Scenario: Verify Criptocurrencies Watchlist
#    Given I Launch CoinMarketCap
#    And I login with email "jayatestuser@gmail.com" and password "Password@123"
#    When Add random Criptocurrencies between 5 and 10 to Watchlist
#    And I Launch CoinMarketCap in new browser
#    And Open Watchlist in new browser
#    And Verify that all the selected Criptocurrencies  between 5 and 10 are added to the Watchlist

  @Test3
  Scenario: Verify Criptocurrencies Ranking
    Given I Launch CoinMarketCap
    When I click "Ranking" from Cryptocurrencies dropdown
    And Select Existing Filter Combination For Industry "Marketplace"
  Then Compare the Results with Ranking page
#    Then Verify that
   