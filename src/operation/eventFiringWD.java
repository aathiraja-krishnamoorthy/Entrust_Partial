package operation;

//package raw.selenium;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class eventFiringWD {
	// private EventFiringWebDriver efd = null;
	WebDriver driver;

	// @BeforeClass
	// public void beforeClass() throws MalformedURLException {
	// // DesiredCapabilities dc = new DesiredCapabilities();
	// System.setProperty("webdriver.firefox.bin",
	// "E:\\Mozilla Firefox\\firefox.exe");
	// // dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
	// // URL url = new URL("http://localhost:4444/wd/hub");
	// // RemoteWebDriver rwd = new RemoteWebDriver(url, dc);
	// WebDriverEventListener eventListener = new MyEventListener();
	// driver = new EventFiringWebDriver(new FirefoxDriver())
	// .register(eventListener);
	// // efd = new EventFiringWebDriver(null);
	// // efd.register(new MyEventListener());
	//
	// }

	// public static class MyEventListener extends
	// AbstractWebDriverEventListener {
	// @Override
	// public void afterChangeValueOf(WebElement element, WebDriver driver) {
	// super.afterChangeValueOf(element, driver);
	// System.out.println("afterChangeValueOf was invoked");
	//
	// }
	//
	// }
	public static class MyEventListener extends AbstractWebDriverEventListener {

		@Override
		public void onException(Throwable throwable, WebDriver driver) {
			DateFormat dateFormat = new SimpleDateFormat(
					"dd_MMM_yyyy__hh_mm_ssaa");
			String destDir = "target/surefire-reports/screenshots";
			new File(destDir).mkdirs();
			String destFile = dateFormat.format(new Date()) + ".png";
			// FrameworkMethod frameworkMethod = new FrameworkMethod(null);
			System.out.println("Exception was triggered");
			super.onException(throwable, driver);
			WebDriver wd = new Augmenter().augment(driver);
			File f = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(f, new File(destDir + "/" + destFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
