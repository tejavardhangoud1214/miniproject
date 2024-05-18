package herokuappExercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HerokuTest {

	WebDriver driver;
	HerokuHome home;

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		this.home = new HerokuHome(driver);

		driver.manage().window().maximize();
		driver.get("http:admin:admin@the-internet.herokuapp.com/");

	}

	@Test
	public void successfulLogin() {
		home.sendCredentials();
		String expectedMessage = "Congratulations! You must have the proper credentials.";
		
		Assert.assertTrue(home.sucessfulMessageDisplayed(expectedMessage));

	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
