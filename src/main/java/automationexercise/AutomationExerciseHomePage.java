package automationexercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;

import screenshot.ScreenShot;

public class AutomationExerciseHomePage {

	WebDriver driver;
	Wait<WebDriver> wa;
	By homePage = By.linkText("Home");
	By contactUsButton = By.linkText("Contact us");
	By getInTouchButton = By.cssSelector(".title.text-center");
	By nameInput = By.name("name");
	By emailInput = By.name("email");
	By subjectInput = By.name("subject");
	By messageInput = By.name("message");
	By uploadFile = By.name("upload_file");
	By fileSubmmitButton = By.name("submit");
	By sucessMessage = By.cssSelector(".status.alert.alert-success");
	By homeButton = By.linkText("Home");
	By frameOne = By.xpath("//iframe[@id='google_esf']/following-sibling::ins/child::div/child::iframe");
	By frameTwo = By.xpath("//div[@id='creative']/child::iframe");

	By closeAd = By.cssSelector("div[id='dismiss-button']");

	AutomationExerciseHomePage(WebDriver driver) {
		this.driver = driver;
		this.wa = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(90))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);

	}

	public boolean isHomePageVisible() {

		WebElement homePageVisible = driver.findElement(homePage);

		String originalHomePageColor = "color: orange;";

		String homePageColor = homePageVisible.getAttribute("style");

		WebElement contactButton = wa.until(ExpectedConditions.visibilityOfElementLocated(contactUsButton));
		contactButton.click();

		if (originalHomePageColor.equals(homePageColor)) {
			Reporter.log("home landed page sucessfully displayed");
		}

		return originalHomePageColor.equals(homePageColor);

	}

	public boolean getInTouchButtonVisible() {

		if (driver.findElement(getInTouchButton).isDisplayed()) {
			Reporter.log("Get in Touch is visible");
		}

		return driver.findElement(getInTouchButton).isDisplayed();

	}

	public void fileUploadSubmmit() {
		driver.findElement(nameInput).sendKeys("first");
		driver.findElement(emailInput).sendKeys("first@gmail.com");
		driver.findElement(subjectInput).sendKeys("happy");
		driver.findElement(messageInput).sendKeys("first class");
		driver.findElement(contactUsButton).sendKeys("contact info");
		driver.findElement(uploadFile).sendKeys("/Users/kalaltejavardhangoud/Downloads/Birth and Death Dataset.xlsx");
		driver.findElement(fileSubmmitButton).click();
		driver.switchTo().alert().accept();
	}

	public boolean successMessageDisplay() {

		String fileUploadMessage = driver.findElement(sucessMessage).getText();
		String actualMessage = "Success! Your details have been submitted successfully.";

		ScreenShot img = new ScreenShot();
		img.screenShot(driver, "scenraio2FilesubmittedSucessfully");
		if (fileUploadMessage.equals(actualMessage)) {
			Reporter.log("details has been submitted sucessfully");
		}

		return fileUploadMessage.equals(actualMessage);

	}

	public String homePageIsLanded() {

		driver.findElement(homeButton).click();

		String b = "";
		try {

			WebElement frameOn = wa.until(ExpectedConditions.visibilityOfElementLocated(frameOne));
			driver.switchTo().frame(frameOn);
			
			driver.switchTo().frame("ad_iframe");
			
			// Wait for the close button to be visible
			WebElement closeButton = wa.until(ExpectedConditions.visibilityOfElementLocated(closeAd));

			// Click on the close button to close the ad
			closeButton.click();
			WebElement homePageLandedColor = driver.findElement(homePage);
			String landedPageColor = "color: orange;";
			b = homePageLandedColor.getAttribute("style");

		} catch (Exception e) {
			driver.switchTo().parentFrame();

			WebElement closeButton = wa.until(ExpectedConditions.visibilityOfElementLocated(closeAd));

			// Click on the close button to close the ad
			closeButton.click();
			WebElement homePageLandedColor = driver.findElement(homePage);
			String landedPageColor = "color: orange;";
			b = homePageLandedColor.getAttribute("style");
			

		}

		
		if(b.equals("color: orange;"))
		{
			Reporter.log("It is navigated back sucessfully to home page");
		}
		return b;

	}

}
