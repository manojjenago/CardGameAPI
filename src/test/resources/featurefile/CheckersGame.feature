Feature: Deck of Cards API Testing


  @chrome
  Scenario: Verify Card Game using API
    Given I navigate to the deck of cards API
    Then Confirm the site is up or verify successful response
    Then Get a new deck
    When User shuffle the deck
    Then Deal three cards to each of two players
#    Then Check whether either has blackjack



