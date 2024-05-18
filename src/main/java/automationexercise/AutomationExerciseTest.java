package automationexercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AutomationExerciseTest {

	WebDriver driver;
	AutomationExerciseHomePage homePage;

	@Parameters({"browser"})
	@BeforeMethod
	public void setUp(String browser) {
		
		if(browser.equals("chrome")) {
		driver = new ChromeDriver();
		}
		else
		{
			driver=new EdgeDriver();
		}
		this.homePage = new AutomationExerciseHomePage(driver);
		driver.manage().window().maximize();
		driver.get("http://automationexercise.com");

	}

	@Test(description="Verify file is uploading and navigating back to the home page")

	public void uploadFileVerifyMessage() {

		String landedPageColor = "color: orange;";
		
		//verify it is landed to home page sucessfully
		Assert.assertTrue(homePage.isHomePageVisible());

		Assert.assertTrue(homePage.getInTouchButtonVisible());
		

		homePage.fileUploadSubmmit();

		Assert.assertTrue(homePage.successMessageDisplay());
		Reporter.log("details have been sucessfully submmited");

		String b = homePage.homePageIsLanded();

		Assert.assertEquals(landedPageColor, b);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
