package com.cucumber.framework.stepdefinition;

import com.cucumber.framework.helper.PageObject.homepage.HomePage;
import com.cucumber.framework.settings.ObjectRepo;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

//ManojKumarJena='Manoj Jena'
//        2/27/2024

public class CheckersGame {
    private HomePage hPage;

    @Given("^: Navigate to \"([^\"]*)\"$")
    public void navigateTo(String webSiteURL) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        String webSiteName = ObjectRepo.reader.getWebsite(webSiteURL);
        System.out.println("HomePage--" + webSiteName);
        Thread.sleep(3000);
        ObjectRepo.driver.get(ObjectRepo.reader.getWebsite(webSiteURL));
        hPage = new HomePage(ObjectRepo.driver);



    }

    @Then("^: Confirm that site is up$")
    public void confirmThatSiteIsUp() {

        hPage.isSiteUp();


    }

    @Then("^: Make five legal moves as orange$")
    public void makeFiveLegalMovesAsOrange() {

        hPage.make5Moves();

    }

    @Then("^: Restart the game$")
    public void restartTheGame() throws InterruptedException {
        hPage.restartGame();
    }

    @Then("^: Confirm that site is up for Card Game$")
    public void confirmThatSiteIsUpForCardGame() {
        hPage.isCardGameSiteUp();

    }

    @Then("^: Get a new Deck$")
    public void getANewDeck() {


    }
}
