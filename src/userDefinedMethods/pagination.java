package userDefinedMethods;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class pagination {
	int[] num = new int[4];
	int i = 0;

	@Test
	public void main() throws IOException, InterruptedException {
		// System.setProperty("phantomjs.binary.path",
		// System.getProperty("user.dir")+"/drivers/phantomjs.exe");
		// WebDriver driver = new PhantomJSDriver();
		// WebDriver driver = new HtmlUnitDriver();
		System.setProperty("webdriver.firefox.bin",
				"E:\\Mozilla Firefox\\firefox.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Pattern p = Pattern.compile("-?\\d+");
		String url = driver.getCurrentUrl();
		driver.get("http://aimprelive.softwaystaging.com/standard");
		System.out.println("Page Loaded");
		driver.findElement(By.xpath("//*[@id='login-email']")).sendKeys(
				"softway_admin");
		driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys(
				"softway.123456");
		driver.findElement(By.xpath("//*[@id='form-login']/div[4]/div/button"))
				.click();
		System.out.println("Page Loaded 1 ");
		driver.findElement(
				By.xpath("//a[contains(string(),'Enrollment Tools') and @href='#']"))
				.click();
		driver.findElement(
				By.xpath("//a[contains(string(),'Lead Lists') and @href='#']"))
				.click();
		driver.findElement(
				By.xpath("//a[contains(string(),'Co-op Lead Lists')]")).click();
//		driver.findElement(
//				By.xpath("//*[@id='LeadSearchKey']")).sendKeys("TEST");
		driver.findElement(By.xpath("//*[@id='searchForm']/div[2]/div/div[2]/div/span/button")).click();
//		driver.findElement(By.xpath("//*[@id='advance_search_icon']")).click();
//		driver.findElement(By.xpath("//*[@id='LeadCreditGrade_chosen']/ul/li/input")).click();
//		driver.findElement(By.xpath("//*[@id='LeadCreditGrade_chosen']/div/ul/li[1]")).click();
//		String str = driver.findElement(By.xpath("//*[@id='LeadCreditGrade_chosen']/ul/li/input")).getText();
		String str = driver.findElement(
				By.xpath("//*[@id='LeadSearchKey']")).getAttribute("value");	
		System.out.println("STR : "+str);
		Matcher m = p.matcher(driver.findElement(
				By.xpath("//ul[@class='pagination custom-pagination']//li[1]"))
				.getText());
		System.out
				.println("Text : "
						+ driver.findElement(
								By.xpath("//ul[@class='pagination custom-pagination']//li[1]"))
								.getText());
		while (m.find()) {
			// System.out.println(m.group(0));
			num[i] = Integer.parseInt(m.group(0));
			i++;
		}
		System.out.println("1 : " + num[0]);
		System.out.println("2 : " + num[1]);
		System.out.println("3 : " + num[2]);
		System.out.println("4 : " + num[3]);

		int lastThreePage = num[1] - 3;
		if (num[1] > 10) {
			int i = 1;
			while ((i <= num[1])) {
				if (i < 4) {
					System.out.println("i : " + i);

				}
				if (i > lastThreePage) {
					System.out.println("i : " + i);

				}
				i++;
			}
		}
		// System.out.println(m);
		// //input[1][@id='button' and @value='Edit']")
	}
}
