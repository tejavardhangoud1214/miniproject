//package jetbluehomepage;

package jetbluehomepage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;

import screenshot.ScreenShot;

public class JetBlueHomePage extends JetBlueTest {

	WebDriver driver;
	Wait<WebDriver> wa;

	By cookieDialougeBox = By.cssSelector(".truste_box_overlay_inner");
	By cookieFrame = By.cssSelector("iframe[class='truste_popframe']");
	By cookieAccept = By.className("call");
	By departureCity = By.xpath("(//input[@type='text'])[position()=1]");

	By destinationCityField = By.xpath("(//input[@type='text'])[position()=2]");
	By departureDateFieldBox = By.cssSelector("jb-flyout-inner[role='dialog']");
	By departureDateField = By
			.xpath("(//input[starts-with(@aria-label,'Depart Press DOWN ARROW key ')])[position()=1]");
	By returnDateField = By.xpath("(//input[starts-with(@aria-label,'Return Press DOWN ARROW key to')])[position()=1]");
	By searchFlightsButton = By.cssSelector("button[data-qaid='searchFlight']");
	By continueFlightBox = By.cssSelector("div[role='dialog']");
	By continuFlighTButton = By.cssSelector("button[data-qaid='continueToNextWarningMessage']");
	By flightResult = By.xpath("//button[@theme='ctaPrimary']");
	By flightResultMessage = By.cssSelector(".card-head.core-blue");
	By flightResultBox = By.cssSelector("div[class='bg-white']");
	By errorBox = By.xpath("//h4[normalize-space(text())='Error']");
	By flightAvilabelMessage = By.cssSelector("h2[id='flight-details']>div");

	public JetBlueHomePage(WebDriver driver) {
		this.driver = driver;
		this.wa = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(90))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);

	}

	
	public void dialogeBoxAccept() {
		WebElement dailogeBox = wa.until(ExpectedConditions.visibilityOfElementLocated(cookieDialougeBox));

		WebElement frameCookie = wa.until(ExpectedConditions.visibilityOfElementLocated(cookieFrame));

		driver.switchTo().frame(frameCookie);

		WebElement cookieButton = wa.until(ExpectedConditions.visibilityOfElementLocated(cookieAccept));
		cookieButton.click();

		wa.until(ExpectedConditions.invisibilityOfElementLocated(cookieDialougeBox));

		driver.switchTo().defaultContent();

	}

	public void enterDepartureCity(String city) {

		WebElement depatureCityName = wa.until(ExpectedConditions.visibilityOfElementLocated(departureCity));
		depatureCityName.click();
		depatureCityName.clear();
		depatureCityName.sendKeys(city);
		depatureCityName.sendKeys(Keys.ENTER);
		Reporter.log("departure city name antered sucessfully");

	}

	public void enterDestinationCity(String city) {

		WebElement destinationCityName = wa.until(ExpectedConditions.visibilityOfElementLocated(destinationCityField));
		destinationCityName.click();
		destinationCityName.sendKeys(city);
		destinationCityName.sendKeys(Keys.ENTER);
		Reporter.log("destination city name antered sucessfully");

	}

	public void enterDepartureDate(String date) {

		WebElement depatureDateBox = wa.until(ExpectedConditions.visibilityOfElementLocated(departureDateFieldBox));
		WebElement departureDate = wa.until(ExpectedConditions.visibilityOfElementLocated(departureDateField));
		departureDate.click();
		departureDate.sendKeys(date);
		departureDate.sendKeys(Keys.ENTER);

	}

	public void enterReturnDate(String date) {
		WebElement returnDate = wa.until(ExpectedConditions.visibilityOfElementLocated(returnDateField));
		returnDate.click();
		returnDate.sendKeys(date);
		returnDate.sendKeys(Keys.ENTER);

	}

	
	  //flight not availabel results
	  public String SearchFlightsNotAvailabel() {
	  
	  String actualMessage=""; 
	  WebElement searchFlight = wa.until(ExpectedConditions.visibilityOfElementLocated(searchFlightsButton));
	 
	  searchFlight.click();
	 
	 wa.until(ExpectedConditions.visibilityOfElementLocated(continueFlightBox));
	  WebElement flight =
	  wa.until(ExpectedConditions.visibilityOfElementLocated(continuFlighTButton));
	  flight.click();
	 
	 WebElement flightRe =
	 wa.until(ExpectedConditions.visibilityOfElementLocated(flightResult));
	 flightRe.click();
	 
	  try {
	 
	  WebElement errorHeading =
	  wa.until(ExpectedConditions.visibilityOfElementLocated(errorBox));
	  Reporter.log(errorHeading.getText()); actualMessage=errorHeading.getText();
	  ScreenShot img = new ScreenShot(); img.screenShot(driver,"wrongSelectionMessageDisplayed");
	  
	  
	  } catch (Exception e) {
	 
	  WebElement message =
	  wa.until(ExpectedConditions.visibilityOfElementLocated(flightResultMessage));
	  actualMessage=message.getText();
	 
	 ScreenShot img = new ScreenShot(); img.screenShot(driver, "scenarioOneFlightNotPresent");
	 
	 
	  }
	 
	   return actualMessage;
	  
	  }
	 
    //flight availabel results
	public String  SearchFlightsAvailabeValid() {
		String message = "";

		WebElement searchFlight = wa.until(ExpectedConditions.visibilityOfElementLocated(searchFlightsButton));
		searchFlight.click();

		WebElement flightAvailabelMessage = wa
				.until(ExpectedConditions.visibilityOfElementLocated(flightAvilabelMessage));
		message = flightAvailabelMessage.getText();
		ScreenShot img = new ScreenShot();
		img.screenShot(driver, "scenarioOneFlightPresent");

		message = flightAvailabelMessage.getText();
		
		

		return message;

	}

}
