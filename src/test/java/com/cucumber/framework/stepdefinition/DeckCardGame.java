package com.cucumber.framework.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
//  ManojKumarJena='Manoj Jena'
//        2/27/2024

public class DeckCardGame {

    private String deckId;
    private String playerOneId;
    private String playerTwoId;

    @Given("^I navigate to the deck of cards API$")
    public void iNavigateToTheDeckOfCardsAPI() {

        RestAssured.baseURI = "https://deckofcardsapi.com/";


    }

    @Then("^Confirm the site is up or verify successful response$")
    public void confirmTheSiteIsUpOrVerifySuccessfulResponse() {
        Response response = RestAssured.get("/");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("^Get a new deck$")
    public void getANewDeck() {

        Response response = RestAssured.get("/api/deck/new/");
        Object newDeckId = response.jsonPath().getString("deck_id");
        Boolean isshuffled = Boolean.valueOf(response.jsonPath().getString("shuffled"));
        Assert.assertNotNull(newDeckId);

    }

    @When("^User shuffle the deck$")
    public void userShuffleTheDeck() {
        // Shuffle the deck
        Response response = RestAssured.get("/api/deck/new/shuffle/?deck_count=1");
        Object newDeckId = response.jsonPath().getString("deck_id");
        Assert.assertNotNull(newDeckId);
        Boolean isshuffled = Boolean.valueOf(response.jsonPath().getString("shuffled"));
        Assert.assertEquals(isshuffled, true);
//        Response response1 =
//                given().log().all()
//                        .accept(ContentType.JSON)
//                        .basePath("/api/deck/new/shuffle/?deck_count=")
//                        .queryParam("count", 5)
//                        .when()
//                        .get();

    }

    @Then("^Deal three cards to each of two players$")
    public void dealThreeCardsToEachOfTwoPlayers() {
        Response response = RestAssured.get("/api/deck/" + deckId + "/draw/?count=3");
        playerOneId = response.jsonPath().getString("deck_id");
        playerTwoId = response.jsonPath().getString("deck_id");

    }


    @Then("^Check whether either has blackjack$")
    public void checkWhetherEitherHasBlackjack() {
        // Check if either player has blackjack
        boolean playerOneHasBlackjack = checkForBlackjack(playerOneId);
        boolean playerTwoHasBlackjack = checkForBlackjack(playerTwoId);

        // Output which player has blackjack
        if (playerOneHasBlackjack) {
            System.out.println("Player One has blackjack!" + playerOneId);
        }
        if (playerTwoHasBlackjack) {
            System.out.println("Player Two has blackjack!" + playerTwoId);
        }


    }

    private boolean checkForBlackjack(String playerId) {
        // Check if a player's hand has blackjack

//        https://deckofcardsapi.com/api/deck/<<deck_id>>/pile/<<pile_name>>/add/?cards=AS,2S
        Response response = RestAssured.get("/api/deck/" + deckId + "/pile/ " + playerId + "/list/");
        String remainingCards = response.jsonPath().getString("remaining");

        //Assuming the  Blackjack is possible only with exactly two cards A and any card more than 10
        return remainingCards.equalsIgnoreCase("2");
    }
}
