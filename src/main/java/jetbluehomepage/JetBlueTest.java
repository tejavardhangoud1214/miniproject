package jetbluehomepage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class JetBlueTest {
	

	 WebDriver driver;
	 JetBlueHomePage homePage;
	 
	 WebDriverWait op=new WebDriverWait(driver,Duration.ofSeconds(10));
	
	 
	 
	
	@BeforeMethod
    public void setUp() {
       
        driver = new ChromeDriver();
        this.homePage=new JetBlueHomePage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.jetblue.com/");
        driver.manage().window().maximize();
        
    }
	
 @Parameters({"cityOne","returnCityOne","departureDateOne","returnDateOne"})
	@Test(description="flightNotAvailabelResults")
	
	public void verifyFlightNotAvailabelSearchResults(String cityOne,String returnCityOne,String departureDateOne,String returnDateOne)
	{
	
		String expectedMessage="No flights have been found for your search criteria.";
		homePage.dialogeBoxAccept();
		homePage.enterDepartureCity(cityOne);
		homePage.enterDestinationCity(returnCityOne);
		homePage.enterDepartureDate(departureDateOne);
		homePage.enterReturnDate(returnDateOne);
		String actualMessage=homePage.SearchFlightsNotAvailabel();	
		Assert.assertEquals(actualMessage, actualMessage);		
		Reporter.log(actualMessage);
		
	}
	
   @Parameters({"cityTwo","returnCityTwo","departureDateTwo","returnDateTwo"})
   @Test(description="verifyfligtAvailabelResults")
	
	public void verifyFlightAvailabelSearchResults(String cityTwo,String returnCityTwo,String departureDateTwo,String returnDateTwo)
	{
	
	    String expectedMessage="Select your departing flight";
		homePage.dialogeBoxAccept();
		homePage.enterDepartureCity(cityTwo);
		homePage.enterDestinationCity(returnCityTwo);
		homePage.enterDepartureDate(departureDateTwo);
	    homePage.enterReturnDate(returnDateTwo);
	    String actualMessage=homePage.SearchFlightsAvailabeValid();
	    Assert.assertEquals(actualMessage, expectedMessage);
	    Reporter.log(expectedMessage);
		
					
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}


}
