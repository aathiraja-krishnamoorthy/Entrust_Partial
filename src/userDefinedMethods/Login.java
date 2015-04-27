package userDefinedMethods;

import java.io.IOException;
import java.util.Properties;

import operation.ReadProperties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	public void login(WebDriver driver) throws IOException {

		ReadProperties objects = new ReadProperties();
		Properties allProperties = objects.getAllRepository();

		driver.get("http://aimprelive.softwaystaging.com/standard");
		driver.findElement(
				By.xpath(allProperties.getProperty("username_xPath")))
				.sendKeys(allProperties.getProperty("username"));
		driver.findElement(
				By.xpath(allProperties.getProperty("password_xPath")))
				.sendKeys(allProperties.getProperty("password"));
		driver.findElement(
				By.xpath(allProperties.getProperty("LoginButton_xPath")))
				.click();
	}

}
