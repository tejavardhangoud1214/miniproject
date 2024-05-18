package herokuappExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import screenshot.ScreenShot;

public class HerokuHome {
	
	WebDriver driver;
	WebDriver wa;
	
	By basicAuthPage=By.linkText("Basic Auth");
	By sucessfullMessage=By.tagName("p");
	
	
	HerokuHome(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	public void sendCredentials()
	{
		
	   driver.findElement(basicAuthPage).click();
		
	}
	
	public boolean sucessfulMessageDisplayed(String expectedMessage)
	{
		ScreenShot op=new ScreenShot();
		op.screenShot(driver,"fourthSecenraioValiCredantialMessage");
		if(driver.findElement(sucessfullMessage).getText().equals(expectedMessage))
		{
			Reporter.log(expectedMessage);
		}
		return driver.findElement(sucessfullMessage).getText().equals(expectedMessage);
	}
	

}
