package operation;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import userDefinedMethods.Login;
import userDefinedMethods.sorting;

public class UIOperation {

	WebDriver driver;

	public UIOperation(WebDriver driver) {
		this.driver = driver;
	}

	public void perform(Properties p, String operation, String objectName,
			String objectType, String value) throws Exception {
		System.out.println("");

		if (operation != null) {
			switch (operation.toUpperCase()) {
			case "CLICK":
				// Perform click
				driver.findElement(this.getObject(p, objectName, objectType))
						.click();
				// driver.findElement(By.xpath("//*[@class='chosen-results']/li[normalize-space(text())="+value+"]"));
				break;
			case "NAVIGATE TO":
				driver.navigate().to(p.getProperty(value));
				break;
			case "NAVIGATE BACK":
				driver.navigate().back();
				break;
			case "NAVIGATE FORWARD":
				driver.navigate().forward();
				break;
			case "PAGE REFRESH":
				driver.navigate().refresh();
				break;
			case "SET TEXT":
				// Set text on control
				driver.findElement(this.getObject(p, objectName, objectType))
						.sendKeys(value);
				break;

			case "GOTO URL":
				// Get url of application
				driver.get(p.getProperty(value));
				break;
			case "GET TEXT":
				// Get text of an element
				driver.findElement(this.getObject(p, objectName, objectType))
						.getText();
				break;
				
			case "PAUSE":
				// Get text of an element
		        Thread.sleep(1000L);
				break;	

			case "RADIO SELECT":
				// Perform click
				driver.findElement(this.getObject(p, objectName, objectType))
						.click();
				// driver.findElement(By.xpath("//*[@class='chosen-results']/li[normalize-space(text())="+value+"]"));
				break;

			case "VERIFY RADIO SELECTED":
				// Perform click
				driver.findElement(this.getObject(p, objectName, objectType))
						.getAttribute("checked");
				/*********************************************************
				 * 
				 * ADD ASSERT
				 * 
				 ********************************************************/
				break;

			case "VERIFY CHECK BOX SELECTED":
				// Perform click
				driver.findElement(this.getObject(p, objectName, objectType))
						.getAttribute("checked");
				/*********************************************************
				 * 
				 * ADD ASSERT
				 * 
				 ********************************************************/
				break;

			case "VERIFY TITLE":
				// Perform click
				String actualTitle = driver.getTitle();
				String expectedTitle = value;
				if (actualTitle.equals(expectedTitle))
					/*********************************************************
					 * 
					 * ADD ASSERT
					 * 
					 ********************************************************/
					break;

			case "VERIFY ELEMENT DISPLAYED":
				// Perform click
				driver.findElement(this.getObject(p, objectName, objectType))
						.isDisplayed();
				/*********************************************************
				 * 
				 * ADD ASSERT
				 * 
				 ********************************************************/
				break;
			case "VERIFY ELEMENT SELECTED":
				// Perform click
				driver.findElement(this.getObject(p, objectName, objectType))
						.isSelected();
				/*********************************************************
				 * 
				 * ADD ASSERT
				 * 
				 ********************************************************/
				break;

			case "DELETE COOKIES":
				// Get text of an element
				driver.manage().deleteAllCookies();
				break;
			case "SELECT RANDOM":
				// Get text of an element
				WebElement drpDwnList = driver.findElement(this.getObject(p,
						objectName, objectType));
				Select sel = new Select(drpDwnList);
				List<WebElement> weblist = sel.getOptions();
				int iCnt = weblist.size();
				Random num = new Random();
				int iSelect = num.nextInt(iCnt);
				sel.selectByIndex(iSelect);
				// driver.findElement(this.getObject(p, objectName,
				// objectType)).getText();
				break;

			case "LOGIN":
				Login log = new Login();
				log.login(driver);
				break;

			case "SORT":
				sorting sort = new sorting();
				sort.sort(driver, value);
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Find element BY using object type and value
	 * 
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Properties p, String objectName, String objectType)
			throws Exception {
		// Find by xpath
		if (objectType.equalsIgnoreCase("XPATH")) {

			return By.xpath(p.getProperty(objectName));
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {

			return By.className(p.getProperty(objectName));

		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {

			return By.name(p.getProperty(objectName));

		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {

			return By.cssSelector(p.getProperty(objectName));

		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {

			return By.linkText(p.getProperty(objectName));

		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {

			return By.partialLinkText(p.getProperty(objectName));

		} else {
			throw new Exception("Wrong object type");
		}
	}
}
