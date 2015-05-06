package testCases;

import org.junit.Before;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import operation.ReadProperties;
import operation.UIOperation;
import operation.dataSplit;
import operation.eventFiringWD.MyEventListener;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import excelExportAndFileIO.ReadExcel;

public class HybridExecuteTest {
	ArrayList<Object> testStepObject = new ArrayList<Object>();
	// List<Integer> items = new ArrayList<Integer>();
	// static Object[][] step = new Object[50][5];
	static Object[][] test = new Object[1][5];
	int last = 1;
	int lastrow = 1;
	int k = 1;
	int testCaseStart = 0;
	private static String suite[][];
	static String[] sheetY, sTestCase_ID, sExecute, sTestCase_Name, sIteration,
			sFunctional_Area, tComment, tAction, tLabelName, tObjectName,
			tParent_Object, tInputData, tExpectedData;
	static String[] fileName, fileExecute, fileY;

	WebDriver webdriver = null;

	// @BeforeClass
	// public void setupBeforeSuite(ITestContext context) {
	// System.setProperty("webdriver.firefox.bin",
	// "E:\\Mozilla Firefox\\firefox.exe");
	// webdriver = new FirefoxDriver();
	// context.setAttribute(ScreenshotReportNGUtils.DRIVER_ATTR, webdriver);
	// }

	@BeforeClass
	@Parameters("browser")
	public void setup(@Optional("Firefox") String browser)
			throws MalformedURLException, Exception {
		// Check if parameter passed from TestNG is 'firefox'
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			System.setProperty("webdriver.firefox.bin",
					"E:\\Mozilla Firefox\\firefox.exe");
			// webdriver = new FirefoxDriver();
			WebDriverEventListener eventListener = new MyEventListener();
			webdriver = new EventFiringWebDriver(new FirefoxDriver())
					.register(eventListener);
			System.out.println("Firefox Loaded...");
		} else if (browser.equalsIgnoreCase("chrome")) {
			File file = new File(System.getProperty("user.dir")
					+ "/drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());
			// create Chrome instance
			webdriver = new ChromeDriver();
			System.out.println("Chrome Launched.....");
		} else if (browser.equalsIgnoreCase("ie")) {
			File file = new File(System.getProperty("user.dir")
					+ "/drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			// create IE instance
			webdriver = new InternetExplorerDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Before
	public void deleteCookies() {
		webdriver.manage().deleteAllCookies();

	}

	@Test
	public void testTest() {
		System.out.println("################################");
	}

	@Test(dataProvider = "hybridData")
	public void testStep(String testcaseName, String keyword,
			String objectName, String objectType, String value)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println("DATA PROVIDER");
		System.out.println("Test Case Name : " + testcaseName);
		System.out.println("Keyword        : " + keyword);
		System.out.println("Object Name    : " + objectName);
		System.out.println("Object Type    : " + objectType);
		System.out.println("Value          : " + value);

		ReadProperties objects = new ReadProperties();
		Properties allProperties = objects.getAllRepository();
		UIOperation operation = new UIOperation(webdriver);

		// Call perform function to perform operation on UI
		if (value == null)
			value = "";
		operation
				.perform(allProperties, keyword, objectName, objectType, value);

	}

	@DataProvider(name = "hybridData")
	public Object[][] getDataFromDataprovider() throws IOException {
		boolean b1 = false;
		int testStep = 1;
		int d = 1;
		int testCaseStart1 = 1;

		ReadExcel file = new ReadExcel();

		ReadProperties obj = new ReadProperties();

		Properties ppp = obj.getAllRepository();

		// Main Test Files
		Sheet testFiles = file.readExcel(System.getProperty("user.dir")
				+ "/xls/", ppp.getProperty("testFiles"), "Sheet1");
		System.out.println("\n\nTest Files Sheet Loaded....");
		int fileRow = testFiles.getLastRowNum() + 1;
		int fileCol = testFiles.getRow(0).getLastCellNum();
		suite = new String[fileRow][fileCol];
		System.out.println("fileRow : " + fileRow);
		for (int i = 1; i < fileRow; i++) {
			Row row = testFiles.getRow(i);
			for (int j = 0; j < fileCol; j++) {
				Cell cell = row.getCell(j,
						org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);

				if (cell.getStringCellValue() != null) {
					suite[i][j] = cell.getStringCellValue();
				}
			}
		}

		// Load Master sheet which has list of test case to be executed

		fileName = new String[testFiles.getLastRowNum()];
		fileY = new String[testFiles.getLastRowNum()];
		fileExecute = new String[testFiles.getLastRowNum()];
		int count = 0;

		System.out.println("\n\nFiles marked as 'Y' to be tested....");
		for (int k = 1; k < fileRow; k++) {
			if (((suite[k][1] != null) && (suite[k][1].charAt(0)) == 'Y')) {
				fileName[count] = suite[k][0];
				fileExecute[count] = suite[k][1];
				System.out.println("Test File : " + fileName[count]);
				fileY[count] = fileName[count];
				count++;
			}
		}
		// Master Sheet
		for (int fileNo = 0; fileNo < count; fileNo++) {
			System.out
					.println("\n\nLoading 'Master Sheet' of " + fileY[fileNo]);
			Sheet masterSheet = file.readExcel(System.getProperty("user.dir")
					+ "/xls/", fileY[fileNo], "Master");
			System.out.println("Master Sheet Loaded...");

			int rowNum = masterSheet.getLastRowNum() + 1;
			int colNum = masterSheet.getRow(0).getLastCellNum();
			suite = new String[rowNum][colNum];
			for (int i = 1; i < rowNum; i++) {
				Row row = masterSheet.getRow(i);

				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row
							.getCell(
									j,
									org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);

					if (cell.getStringCellValue() != null) {
						suite[i][j] = cell.getStringCellValue();

					}
				}
			}

			sTestCase_ID = new String[masterSheet.getLastRowNum()];
			sExecute = new String[masterSheet.getLastRowNum()];
			sTestCase_Name = new String[masterSheet.getLastRowNum()];
			sIteration = new String[masterSheet.getLastRowNum()];
			sFunctional_Area = new String[masterSheet.getLastRowNum()];

			sheetY = new String[masterSheet.getLastRowNum()];
			int rowMaster = masterSheet.getLastRowNum() + 1;
			int sheetCount = 0;
			System.out.println("Rows in Master Sheet : " + rowMaster);

			System.out.println("\n\nSheets to be tested (passing 'Y').... ");
			for (int k = 1; k < rowMaster; k++) {
				if (((suite[k][1] != null) && (suite[k][1].charAt(0)) == 'Y')) {
					sTestCase_ID[sheetCount] = suite[k][0];
					sExecute[sheetCount] = suite[k][1];
					sTestCase_Name[sheetCount] = suite[k][2];
					sIteration[sheetCount] = suite[k][3];
					sFunctional_Area[sheetCount] = suite[k][4];
					System.out.println("Test Sheet : "
							+ sTestCase_ID[sheetCount]);

					sheetY[sheetCount] = sTestCase_ID[sheetCount];
					sheetCount++;
				}

			}
			// Test Case Sheets

			tComment = new String[1000];
			tAction = new String[1000];
			tLabelName = new String[1000];
			tObjectName = new String[1000];
			tParent_Object = new String[1000];
			tInputData = new String[1000];
			tExpectedData = new String[1000];
			System.out.println("\n\nSheets Count to be tested : " + sheetCount);
			System.out.println("\n\nTest Case loading....");
			System.out.println("....");
			System.out.println("....");

			for (int iLoop1 = 0; iLoop1 < sheetCount; iLoop1++) {
				System.out.println("\n\nSheet in fetch : " + sheetY[iLoop1]);
				Sheet testSheet = file.readExcel(System.getProperty("user.dir")
						+ "/xls/", fileName[fileNo], sheetY[iLoop1]);
				System.out.println(testSheet.getSheetName()
						+ " Sheet loaded....");

				int row = testSheet.getLastRowNum();

				while (testStep < row) {
					Row tcRow = testSheet.getRow(testStep);
					for (int j = 0; j < 5; j++) {
						Cell cell = tcRow
								.getCell(
										j,
										org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
						test[0][j] = cell.getStringCellValue();
						dataSplit split = new dataSplit();
						if (j == 0) {
							if (!test[0][0].toString().isEmpty()) {
								testCaseStart = testStep;
								testCaseStart--;
							}
							if (b1 == true) {
								if (testCaseStart1 < testCaseStart) {
									if (testStep >= testCaseStart) {
										testStep = testCaseStart1;
									}

								}
							}
						}

						if (cell.getStringCellValue().contains("|")) {

							Object[] temp = split.dataFetch(
									cell.getStringCellValue(), testSheet);

							while (d <= temp.length) {
								b1 = true;
								testStepObject.add(temp[d]);
								d++;
								System.out.println("String Value ::: [" + k
										+ "][" + j + "] : " + testStepObject);
								break;
							}
							if (d == temp.length) {
								testCaseStart1 = testCaseStart;
								b1 = false;
								d = 1;
							}
						} else {
							testStepObject.add(cell.getStringCellValue());
							System.out.println("String Value ::: [" + k + "]["
									+ j + "] : " + testStepObject);

						}

					}

					System.out.println("New Row : " + testStep);
					k++;
					testStep++;
					if (testStep == row) {
						testCaseStart = testStep;
						testCaseStart--;
						if (b1 == true) {
							if (testCaseStart1 < testCaseStart) {
								if (testStep >= testCaseStart) {
									testStep = testCaseStart1;
									testStep++;
								}
								// b1 = false;
							}

						}
					}
				}

				testStep = 1;
				d = 1;
				testCaseStart1 = 0;
				testCaseStart = 0;

				System.out.print("Aathi.....LL\n");

			}// For iLoop2

		}
		int row = testStepObject.size() / 5;
		int z = 0;
		Object[][] step = new Object[row][5];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < 5; j++) {
				step[i][j] = testStepObject.get(z);
				z++;
			}
		}

		return step;
	}

}
