package testCases;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class softway {

	WebDriver driver;
	int sNo = 1;

	// Bot through the sitemap url's and check the page source for the html tags
	// Jsoup is a HTML parser framework
	@Test
	public void test_softway() throws MalformedURLException, IOException {
		System.setProperty("webdriver.firefox.bin",
				"E:\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();

		driver.get("http://www.softwaysolutions.com/sitemap.xml");
		List<WebElement> linkElements = driver.findElements(By
				.xpath("//*[@id='cont']/table/tbody//a"));
		String[] linksText = new String[linkElements.size()];
		System.out.println("Total Links : " + linkElements.size());
		int index = 0;

		for (WebElement element : linkElements) {
			linksText[index] = element.getAttribute("href");
			if (!linksText[index].endsWith(".pdf"))
				tagFetch(linksText[index]);
			index++;
		}
	}

	public void tagFetch(String linksText) throws MalformedURLException,
			IOException {

		Document doc = Jsoup.parse(new URL(linksText), 5000);
		System.out.println(sNo + ". " + linksText);
		log(sNo + ". " + linksText);
		log("");
		log("");
		System.out.println("");
		System.out.println("");
		System.out.println("H1 Tags HTML : ");
		System.out.println("`````````````` ");
		log("H1 Tags HTML : ");
		log("`````````````` ");
		for (Element e : doc.select("h1")) {
			System.out.println("<h1>" + e.html() + "</h1>");
			log("<h1>" + e.html() + "</h1>");
		}
		System.out.println("");
		System.out.println("");
		log("");
		log("");
		System.out.println("H2 Tags HTML : ");
		System.out.println("`````````````` ");
		log("H2 Tags HTML : ");
		log("`````````````` ");

		for (Element e : doc.select("h2")) {
			System.out.println("<h2>" + e.html() + "</h2>");
			log("<h2>" + e.html() + "</h2>");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("Alt attributes HTML : ");
		System.out.println("````````````````````` ");
		log("");
		log("");
		log("Alt attributes HTML : ");
		log("````````````````````` ");
		for (Element e : doc.getElementsByAttribute("alt")) {
			System.out.println(e.outerHtml());
			log(e.outerHtml());
		}
		log("");
		log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		log("");
		log("");
		System.out.println("");
		System.out.println("");
		sNo++;
	}

	public static void log(String message) throws IOException {

		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream("E:\\Softway-Solutions-Source-Tags.txt",
						true)));
		out.println(message);
		out.close();
	}

}
