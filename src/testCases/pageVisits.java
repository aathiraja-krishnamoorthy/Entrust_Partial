package testCases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class pageVisits {

	WebDriver driver;
	static List<String> linkAlreadyVisited = new ArrayList<String>();

	@BeforeClass
	public void setup() throws MalformedURLException, Exception {
		System.setProperty("webdriver.firefox.bin",
				"E:\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.softwaysolutions.com/");

	}


	public pageVisits() {
		System.out.println("pageVisits Constructor...");
	}

	public pageVisits(WebDriver driver) {
		this.driver = driver;
	}


//	public void test() {
//		driver.get("http://aimprelive.softwaystaging.com/rp_realtors");
//		driver.switchTo().alert().accept();
//		System.out.println("@@@@@@@@@@@@@@@");
//
//		driver.get("http://aimprelive.softwaystaging.com/standard/roles/index");
//		System.out.println("###############");
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}

	 @Test
	public void linkTest() {

		// Alert alert;
		// driver.get("http://aimprelive.softwaystaging.com/standard/users/ee_admin_dashboard");

		// start recursive linkText
		// new pageVisits(driver).linkTest();
		// loop over all the a elements in the page
		for (WebElement link : driver.findElements(By.tagName("a"))) {
			// Check if link is displayed and not previously visited
			// System.out.println(link.getText()+" - "+link.getAttribute("href"));
			if (!linkAlreadyVisited.contains(link.getAttribute("href"))
					&& !link.getText().trim().equalsIgnoreCase("Logout")) {
				// add link to list of links already visited
				linkAlreadyVisited.add(link.getAttribute("href"));
				System.out.println(link.getText() + " - "
						+ link.getAttribute("href"));
				// click on the link. This opens a new page
				// link.click();

//				driver.get(link.getAttribute("href"));
				if (ExpectedConditions.alertIsPresent().equals("true")) {
					driver.switchTo().alert().accept();
				}

				// call recursiveLinkTest on the new page
				new pageVisits(driver).linkTest();
			}
		}
		// driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
