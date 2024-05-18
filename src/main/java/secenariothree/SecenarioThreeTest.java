package secenariothree;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import jetbluehomepage.JetBlueHomePage;

public class SecenarioThreeTest {

	WebDriver driver;
	HomePage page;

	

	@BeforeMethod
	public void setUp() {

		driver = new ChromeDriver();
		this.page = new HomePage(driver);

		driver.manage().window().maximize();
		

	}

	@Parameters({"url1","url2","url3","url4"})
	
	@Test

	public void printMaximumLinksPageTitle(String url1,String url2,String url3,String url4) {

		page.storeUrls(url1, url2, url3, url4);
		page.printPageUrlTitleLink();
		page.maxLinkPageTitle();

	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	

}
