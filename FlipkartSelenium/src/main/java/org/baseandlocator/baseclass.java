package org.baseandlocator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseclass {
	public static WebDriver driver;
	public static Actions a;
	public static Robot r;
	public static TakesScreenshot t;
	public static JavascriptExecutor j;
	public static Select s;
	
	
	
	
	//driver

	public static void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static void launchBrowserfirefox() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	
	
	public static void closebrowser() {
		driver.close();
	}

	public static void geturl(String url) {
		driver.get(url);
		
	}
	
	
	public static String title() {
		String title = driver.getTitle();
		return title;
			}
	public static String pageurl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;

	}
	
	
	
	
	//sendkey and click
	

	public static void sendvalue(String text, WebElement ele) {
		ele.sendKeys(text);

	}

	public static void click(WebElement ele) {
		ele.click();
	}
	
	
	
	
	
	//Actions

	public static void movetoelement(WebElement ele) {
		a = new Actions(driver);
		a.moveToElement(ele).perform();

	}

	public static void doubleandcontextclick(WebElement text) {
		a.doubleClick(text).perform();
		a.contextClick(text).perform();
	}

	public static void draganddrop(WebElement source, WebElement destination) {
		a.dragAndDrop(source, destination).perform();
	}
	
	
	
	
	
	//robot

	public static void keypressandrelese(String text) throws AWTException {
		r = new Robot();
		for (int i = 0; i < i; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
		}

		for (int j = 0; j < j; j++) {
			r.keyRelease(KeyEvent.VK_ENTER);
		}
	}
	
	
	
	
	
	//Screenshot

	public static void Screenshot(String img) throws IOException {
		TakesScreenshot s = (TakesScreenshot) driver;
		File as = s.getScreenshotAs(OutputType.FILE);
		File f = new File("Locator + img.png");
		FileUtils.copyDirectory(as, f);
	}
	
	
	
	
	
	//javaScript

	public static void scrolldown(WebElement ele) {
		j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].scrollIntoView(false)", ele);

	}

	public static void scrollup(WebElement ele) {
		j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].scrollIntoView(true)", ele);
	}
	
	
	
	
	// window handling
	
	public static  void windowhandle() {
		String p = driver.getWindowHandle();
		System.out.println(p);
		
		Set<String> c = driver.getWindowHandles();
		System.out.println(c);
		
		for (String e : c) {
			
			if (e != p) {
				
				driver.switchTo().window(p);
				
			}
			
		}
		

	}
	
	
	
	
	
	
	
	
	//data driven

	public static void onecell() throws IOException {
		File f = new File("Excellocator.xlsm");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheet = wb.getSheet("Whichsheet");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
	}
	
	

	public static void allcell() throws IOException {
		File f = new File("Excellocator.xlsm");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheet = wb.getSheet("Whichsheet");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);

		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {

			Row row2 = sheet.getRow(i);

			for (int j = 0; j < row2.getPhysicalNumberOfCells(); j++) {

				Cell c = row2.getCell(j);
				int cellType = c.getCellType();
			}

		}
	}
	
	

	public static void spartcell() throws IOException {
		File f = new File("Excellocator.xlsm");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheet = wb.getSheet("Whichsheet");
		Row row = sheet.getRow(0);
		Cell c = row.getCell(0);

		int cellType = c.getCellType();

		if (cellType == 1) {
			String stringCellValue = c.getStringCellValue();
			System.out.println(stringCellValue);

		} else if (DateUtil.isCellDateFormatted(c)) {
			Date dd = c.getDateCellValue();
			SimpleDateFormat am = new SimpleDateFormat("dd-MMM-yy");
			String format = am.format(dd);
			System.out.println(format);
		} else {
			double nc = c.getNumericCellValue();
			long o = (long) nc;
			String v = String.valueOf(o);
			System.out.println(v);
		}

	}
	
	

	public static void newsheet(int row, int colom, String vale) throws IOException {

		File f = new File("E:\\testing ecl\\createdatadriven\\excel//newfile.xlsx");
		Workbook wk = new XSSFWorkbook();
		Sheet sheet = wk.createSheet("data");

		Row row1 = sheet.createRow(0);
		Cell cell = row1.createCell(0);
		cell.setCellValue("DataDriven");

		FileOutputStream fo = new FileOutputStream(f);
		wk.write(fo);
	}

	public static void newrow(int row, int colom, String vale) throws IOException {
		File f = new File("E:\\testing ecl\\createdatadriven\\excel//newfile.xlsx");
		Workbook wk = new XSSFWorkbook();
		Sheet sheet = wk.createSheet("data");

		Row row1 = sheet.createRow(0);
		Cell cell = row1.createCell(0);
		cell.setCellValue("DataDriven");

		FileOutputStream fo = new FileOutputStream(f);
		wk.write(fo);
	}
	
	

	public static void newcell(int row, int colom, String vale) throws IOException {
		File f = new File("E:\\testing ecl\\createdatadriven\\excel//newfile.xlsx");
		Workbook wk = new XSSFWorkbook();
		Sheet sheet = wk.createSheet("data");

		Row row1 = sheet.createRow(0);
		Cell cell = row1.createCell(0);
		cell.setCellValue("DataDriven");

		FileOutputStream fo = new FileOutputStream(f);
		wk.write(fo);
	}

	
	
	public static void updatecell(int row, int colom, String old, String new1) throws IOException {
		File f = new File("Excellocator.xlsm");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheet = wb.getSheet("Whichsheet");
		Row row1 = sheet.getRow(0);
		Cell cell = row1.getCell(0);
		String scv = cell.getStringCellValue();
		if (scv.equals(old)) {
			cell.setCellValue(new1);
		}

		FileOutputStream s = new FileOutputStream(f);
		wb.write(s);
	}



}
