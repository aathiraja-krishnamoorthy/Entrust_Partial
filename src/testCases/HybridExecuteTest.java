package testCases;

import org.junit.Before;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import excelExportAndFileIO.ReadExcel;

public class HybridExecuteTest {
	// ArrayList<data> al = new ArrayList<data>();
	// List<Integer> items = new ArrayList<Integer>();
	static Object[][] step = new Object[50][5];
	static Object[][] test = new Object[50][5];
	int last = 1;
	int lastrow = 1;
	int k = 1;
	int testCaseStart = 0;
	private static String suite[][];
	static String sFeature, sRun, sDescription, sWindow, sObject, sAction,
			sData, tData, sTitle, sBrowser, sObject_Identifier, sProperty;
	static String[] sheetY, sTestCase_ID, sExecute, sTestCase_Name, sIteration,
			sFunctional_Area, tComment, tAction, tLabelName, tObjectName,
			tParent_Object, tInputData, tExpectedData;
	static String[] fileName, fileExecute, fileY;
	protected static File inputWorkbook;
	static int iFeatureCount = 1;
	static int fileCount = 1;
	static int iStepCount;
	// variable declaration for iKeyword interface
	// static Keyword_Name kAction;
	static int iTime = 5000;
	static WebDriver driver;
	static WebElement element;
	static By bProperty;
	static boolean fBrowser, TestCaseStatus = true;
	Sheet sheet;
	// Variable declaration for IReport interface
	static BufferedWriter output = null;
	static File file;
	static String sDateTime, sFilename, sBodyText;
	String screenshotpath, strDesc;
	String TestExecutionTime;
	static String htmlreport;
	// Variable declaration for IUtility interface
	static int Timeout = 30;

	protected StringBuffer verificationErrors = new StringBuffer();

	// Properties p = new Properties();

	// Properties propObj = prop.getObjectRepository();

	WebDriver webdriver = null;

	// @BeforeClass
	// // public void setupBeforeSuite(ITestContext context) {
	// public void setup() {
	// System.setProperty("webdriver.firefox.bin",
	// "E:\\Mozilla Firefox\\firefox.exe");
	// webdriver = new FirefoxDriver();
	// // context.setAttribute(ScreenshotReportNGUtils.DRIVER_ATTR, webdriver);
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
		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {
			// set path to chromedriver.exe You may need to download it from
			// http://code.google.com/p/selenium/wiki/ChromeDriver
			// System.setProperty("webdriver.chrome.driver","E:\\aathiraja\\Selenium Projects\\Selenium Jar files\\chromedriver.exe");
			File file = new File(System.getProperty("user.dir")
					+ "/drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());
			// create chrome instance
			webdriver = new ChromeDriver();
			System.out.println("Chrome Launched.....");
		} else if (browser.equalsIgnoreCase("ie")) {
			// set path to IEdriver.exe You may need to download it from
			// 32 bits
			// http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_Win32_2.42.0.zip
			// 64 bits
			// http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_x64_2.42.0.zip
			System.setProperty("webdriver.ie.driver", "C:/IEdriver.exe");
			// create chrome instance
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
	public void testLogin(String testcaseName, String keyword,
			String objectName, String objectType, String value)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println("DATA PROVIDER");
		System.out.println("Test Case Name : " + testcaseName);
		System.out.println("Keyword        : " + keyword);
		System.out.println("Object Name    : " + objectName);
		System.out.println("Object Type    : " + objectType);
		System.out.println("Value          : " + value);

		// if(testcaseName!=null&&testcaseName.length()!=0){
		// webdriver=new FirefoxDriver();
		// }
		ReadProperties objects = new ReadProperties();
		Properties allProperties = objects.getAllRepository();
		// Properties object = objects.getObjectRepository();
		// Properties config = objects.getConfigRepository();
		// Properties data=objects.getDataRepository();

		// allProperties.putAll(object);
		// allProperties.putAll(config);
		// allProperties.putAll(data);
		UIOperation operation = new UIOperation(webdriver);
		// Call perform function to perform operation on UI
		if (value == null)
			value = "";
		operation
				.perform(allProperties, keyword, objectName, objectType, value);
		// operation.perform(keyword, objectName, objectType, value);

	}

	@DataProvider(name = "hybridData")
	public Object[][] getDataFromDataprovider() throws IOException {
		System.out.println("TEST TEST....");
		// ArrayList al = new ArrayList();

		// Object[][] data = null;
		// System.out.println("Start...");
		// System.out.println(System.getProperty("user.dir")+
		// "\\"+ppp.getProperty("testCaseFileName"));
		boolean b1 = false;
		int testStep = 1;
		int d = 1;
		int testCaseStart1 = 1;

		ReadExcel file = new ReadExcel();

		ReadProperties obj = new ReadProperties();

		Properties ppp = obj.getAllRepository();

		// Main Test Files
		Sheet testFiles = file.readExcel(System.getProperty("user.dir") + "",
				ppp.getProperty("testFiles"), "Sheet1");
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
		// int lastSheetNo = testFiles.getLastRowNum()+1;

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
					+ "", fileY[fileNo], "Master");
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
			// Vector<String> dynamicRow=new
			// Vector<String>(rowMaster,rowMaster);
			System.out.println("Rows in Master Sheet : " + rowMaster);
			// int[] tcID = new int[guru99Sheet.getLastRowNum()];

			System.out.println("\n\nSheets to be tested (passing 'Y').... ");
			for (int k = 1; k < rowMaster; k++) {
				if (((suite[k][1] != null) && (suite[k][1].charAt(0)) == 'Y')) {
					sTestCase_ID[sheetCount] = suite[k][0];
					sExecute[sheetCount] = suite[k][1];
					sTestCase_Name[sheetCount] = suite[k][2];
					sIteration[sheetCount] = suite[k][3];
					sFunctional_Area[sheetCount] = suite[k][4];
					// System.out.println(guru99Sheet.getLastRowNum());

					// debug("Suite : - Test Case ID : "
					// + sTestCase_ID[iFeatureCount] + " , Execute : "
					// + sExecute[iFeatureCount] + " , Test Case Name : "
					// + sTestCase_Name[iFeatureCount] + " , Iteration : "
					// + sIteration[iFeatureCount]
					// + " , Functional Area : "
					// + sFunctional_Area[iFeatureCount]
					// + "Test Case Count :" + iFeatureCount);
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

			// iFeatureCount=iFeatureCount-1;
			for (int iLoop1 = 0; iLoop1 < sheetCount; iLoop1++) {
				System.out.println("\n\nSheet in fetch : " + sheetY[iLoop1]);
				Sheet testSheet = file.readExcel(System.getProperty("user.dir")
						+ "", fileName[fileNo], sheetY[iLoop1]);
				System.out.println(testSheet.getSheetName()
						+ " Sheet loaded....");

				int row = testSheet.getLastRowNum();
				int col = testSheet.getRow(0).getLastCellNum();
				// TestCase = new Object[row][iColCount];
				// TestCase = new Object[50][iColCount];
				// lastrow = last + row - 1;
				// if (row <= 0)
				// break;
				// for (int i = 1; i < row; i++) {

				while (testStep < row) {
					Row tcRow = testSheet.getRow(testStep);
					for (int j = 0; j < tcRow.getLastCellNum(); j++) {
						Cell cell = tcRow
								.getCell(
										j,
										org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
						test[k][j] = cell.getStringCellValue();
						dataSplit split = new dataSplit();
						// if (test[k][j] != null ||
						// test[k][j].toString().length()!=0) {
						if (j == 0) {
							if (!test[k][0].toString().isEmpty()) {
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
							// for (int d = 1; d < temp.length; d++) {

							while (d <= temp.length) {
								b1 = true;
								step[k][j] = temp[d];
								d++;
								System.out.println("String Value ::: [" + k
										+ "][" + j + "] : " + step[k][j]);
								break;
							}
							if (d == temp.length) {
								testCaseStart1 = testCaseStart;
								b1 = false;
								d = 1;
							}
							// temp = data.getParameterValue("request");
							// Object log = temp == null ? null :
							// (temp.getClass().isArray() ?
							// Arrays.asList((Object[])temp) : temp);

							// TestCase[k][j] = split.dataFetch(
							// cell.getStringCellValue(), testSheet);
						} else {
							step[k][j] = cell.getStringCellValue();
							System.out.println("String Value ::: [" + k + "]["
									+ j + "] : " + step[k][j]);

						}

					}
					// if (TestCase[k][0].toString().isEmpty()
					// && TestCase[k][1].toString().isEmpty()
					// && TestCase[k][2].toString().isEmpty()
					// && TestCase[k][3].toString().isEmpty()) {
					// testCaseStart = testStep;
					// testCaseStart--;
					// }

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

		return step;
	}
	// DATA PROVIDER

	// @DataProvider(name = "hybridData")
	// public Object[][] getData() throws IOException {
	// System.out.println("TESTTESTTEST....");
	// // ArrayList al = new ArrayList();
	// Object[][] TestCase = null;
	//
	// return TestCase;
	// }

}
