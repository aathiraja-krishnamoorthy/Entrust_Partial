package userDefinedMethods;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class dropDownMultiSelect {
	public void dropDownMultiSelectMethod(WebDriver driver) throws IOException {
	List<WebElement> allElements = driver.findElements(By.xpath("//*[@id='UserRoleId_chosen']/div/ul")); 

	for (WebElement element: allElements) {
	      System.out.println(element.getText());
	}
	}
}
