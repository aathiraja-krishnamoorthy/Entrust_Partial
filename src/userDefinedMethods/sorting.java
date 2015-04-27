package userDefinedMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class sorting {
	WebDriver driver;
	int[] num = new int[4];
	int i = 0;
	List<String> displayedNames = new ArrayList<String>();
	List<String> SortedNames = new ArrayList<String>();
	List<WebElement> rowElmt;
	List<WebElement> headers;

	// public sorting(WebDriver driver) {
	// this.driver = driver;
	// }

	public void sort(WebDriver driver, String value) throws IOException,
			InterruptedException {

		// WebElement tableType = driver.findElement(By
		// .xpath("//*[@id='page-content']/div/div[2]/div/table"));
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		Pattern p = Pattern.compile("-?\\d+");
		Matcher m = p.matcher(driver.findElement(
				By.xpath("//ul[@class='pagination custom-pagination']//li[1]"))
				.getText());
		while (m.find()) {
			// System.out.println(m.group(0));
			num[i] = Integer.parseInt(m.group(0));
			i++;
		}

		String url = driver.getCurrentUrl();
		System.out.println("Current URL : " + url);
		if (url.contains("coOpLeads")) {
			System.out.println("Page Loaded is CO-OP ");
			headers = driver
					.findElements(By
							.xpath("//*[@id='page-content']/div/div[2]/table/thead/tr/th"));
		} else if (url.contains("ee_admin_index")) {
			System.out.println("Page Loaded is Manage Lead List ");
			headers = driver
					.findElements(By
							.xpath("//*[@id='page-content']/div/div[2]/table/thead/tr/th"));
		}

		int in = headers.size();
		int j = 0;
		int column = 0;
		while (j < in) {
			if (headers.get(j).getText().equals(value)) {
				column = j;
				column++;
			}
			j++;
		}

		int lastThreePage = num[1] - 3;
		if (num[1] > 9) {
			int i = 1;
			while ((i <= num[1])) {
				if (i < 4) {
					System.out.println("i : " + i);
					driver.get(url + "/page:" + i);
					sort1(driver, column);
				}
				if (i > lastThreePage) {
					System.out.println("i : " + i);
					driver.get(url + "/page:" + i);
					sort1(driver, column);

				}
				i++;
			}
		} else {
			int i = 1;
			while ((i <= num[1])) {
				System.out.println("i : " + i);
				driver.get(url + "/page:" + i);
				sort1(driver, column);
			}

		}

		System.out.println("Displayed Names : ");
		System.out.println(displayedNames);
		Thread.sleep(50);
		List<String> sortingOperation = displayedNames;
		Collections.sort(sortingOperation);
		// Collections.sort(sortingOperation, Collections.reverseOrder());
		System.out.println("Sorted Names : ");
		System.out.println(sortingOperation);
		Assert.assertEquals(SortedNames, sortingOperation);
		System.out.println("Assert  : TRUE");
	}

	public void sort1(WebDriver driver, int column) throws InterruptedException {

		rowElmt = driver.findElements(By
				.xpath("//*[@id='page-content']/div/div[2]/table/tbody//td["
						+ column + "]"));

		String getData;
		Thread.sleep(50);
		for (int i = 0; i < rowElmt.size(); i++) {

			getData = rowElmt.get(i).getText();
			displayedNames.add(getData);
			SortedNames.add(getData);

		}
	}
}
