package com.cucumber.framework.helper.PageObject.homepage;

import com.cucumber.framework.helper.Generic.GenericHelper;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.PageObject.PageBase;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */


public class HomePage extends PageBase {
	
	private static WebDriver driver;
	private GenericHelper genericHelper;
	private WebDriverWait wait;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		 genericHelper = new GenericHelper(driver);
		 wait = new WebDriverWait(driver, 20);
	}
	
	/** Web Elements */


	@FindBy(how=How.XPATH,using="//p[@id='message' and text()='Make a move.']")
	public WebElement makeAMove;



	@FindBy(how=How.XPATH,using="//a[ text()='Restart...']")
	public WebElement restart;


	// Function to check if the site is up
	public void  isSiteUp() {
		String title = driver.getTitle() ;

		log.info("title is :-" + title);

		Assert.assertEquals(title, "Checkers - Games for the Brain");

	}

	// Function to check if it's a new game
// Function to check if it's a new game
	private static boolean isNewGame() {
		// Define the XPath for the element containing the expected text
		String xpath = "//p[@id='message' and text()='Select an orange piece to move.']";

		// Find the element using XPath
		WebElement element = driver.findElement(By.xpath(xpath));

		String TextForNewGame = element.getText();
		System.out.println("New Game Test --" + TextForNewGame);
		// Check if the element exists and contains the expected text
		return element.getText().equals("Select an orange piece to move.");
	}


	// Function to make five move
	public static void make5Moves() {
		List<String> spaces = new ArrayList<>();
		spaces.add("space62");
		spaces.add("space42");
		spaces.add("space22");
		spaces.add("space33");
		spaces.add("space15");

		for (String space : spaces) {

//			boolean isNew = isNewGame();
//			System.out.println("is new Game " + isNew);
			if (isNewGame()) {
				// Handle the scenario when it's a new game
				System.out.println("New game started.");
				makeMove(space);
			} else {
				// Wait for "Make a move" text to appear before you can take a move
				confirmMove();
				// Make the move
				makeMove(space);
			}

		}
	}

	// Function to make a move
	private static void makeMove(String space) {
		WebElement selectedSpace = driver.findElement(By.xpath("//img[@name='"+space+"']"));
		selectedSpace.click();
	}
//	public void make5Moves() {
//
//		// Make five legal moves as orange
//		for (int i = 0; i < 5; i++) {
//			//Check there is text as Make a Move before you can do actual Move
//			confirmMove();
//			// Wait for "Make a move" text to appear before you can take a
//			makeMove();
//
//		}
//
//	}
	// Function to make a move
	private static void makeMove() {
		// Example: Click on space62, assuming orange's turn
		WebElement space62 = driver.findElement(By.name("space62"));
		space62.click();
		// Example: Click on space73 to move to an empty square
		WebElement space73 = driver.findElement(By.name("space73"));
		space73.click();
		// If there's a blue piece, take it (add your logic here)
		// Example: Click on space84 to take the blue piece
		WebElement space84 = driver.findElement(By.name("space84"));
		space84.click();
	}

	// Function to confirm the move
	private static void confirmMove() {
		// Verify that the "Make a move" text is present
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("//p[@id='message' and text()='Make a move.']")));
	}

	// Function to restart the game
	public void restartGame() throws InterruptedException {
		// Click the "Restart game"
		Thread.sleep(2000);

		restart.click();
		Thread.sleep(2000);
	}

	public void isCardGameSiteUp() {

		String title = driver.getTitle() ;

		log.info("title is :-" + title);

		Assert.assertEquals(title, "Deck of Cards API");

	}
}
