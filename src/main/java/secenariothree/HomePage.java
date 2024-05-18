package secenariothree;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;

public class HomePage {

	WebDriver driver;
	Wait<WebDriver> wa;
	ArrayList<String> links = new ArrayList<String>();
	HashMap<String, Integer> pageLinksTitle = new HashMap<String, Integer>();

	HomePage(WebDriver driver) {

		this.driver = driver;
		this.wa = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(90))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);

	}
	public void storeUrls(String url1, String url2, String url3, String url4) {

		 String[] urls = {url1, url2, url3, url4};
	        for (int i = 0; i < urls.length; i++) {
	            driver.get(urls[i]);
	            links.add(driver.getCurrentUrl());
	        }
		

	}

	public void printPageUrlTitleLink() {
		
		for(String currentUrl:links)
		{
			driver.get(currentUrl);

			List<WebElement> count = driver.findElements(By.tagName("a"));
			pageLinksTitle.put(driver.getTitle(), count.size());

			System.out.println("The current page Url:" + driver.getCurrentUrl());
			System.out.println("The current page Title:"+driver.getTitle());
			System.out.println("The current page No.of links in the:"+count.size());
				
			System.out.println(" ");

		}

	}

	public void maxLinkPageTitle() {
		int maxLinks = Integer.MIN_VALUE;
		int presentKey = 0;
		String maxPageTitle = "";
		
		for (Map.Entry<String, Integer> a : pageLinksTitle.entrySet()) {
			presentKey = a.getValue();
			if (presentKey > maxLinks) {
				maxLinks = presentKey;
				maxPageTitle = a.getKey();

			}

		}
		//𝐏𝐚𝐠𝐞 𝐰𝐢𝐭𝐡 𝐌𝐚𝐱𝐢𝐦𝐮𝐦 𝐋𝐢𝐧𝐤𝐬: [𝐏𝐚𝐠𝐞 𝐓𝐢𝐭𝐥𝐞] - [𝐍𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐋𝐢𝐧𝐤𝐬] 𝐥𝐢𝐧𝐤𝐬
		System.out.println("𝐏𝐚𝐠𝐞 𝐰𝐢𝐭𝐡 𝐌𝐚𝐱𝐢𝐦𝐮𝐦 𝐋𝐢𝐧𝐤𝐬:"+"["+maxPageTitle+"]"+"-"+"["+maxLinks+"]"+"links");
		Reporter.log("𝐏𝐚𝐠𝐞 𝐰𝐢𝐭𝐡 𝐌𝐚𝐱𝐢𝐦𝐮𝐦 𝐋𝐢𝐧𝐤𝐬:"+"["+maxPageTitle+"]"+"-"+"["+maxLinks+"]"+"links");

	}

}
