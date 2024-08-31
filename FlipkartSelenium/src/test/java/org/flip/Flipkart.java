package org.flip;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.baseandlocator.baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Flipkart extends baseclass {
	public static void main(String[] args) throws Exception  {
		
		launchBrowser();

		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();

	
		WebElement move = driver.findElement(By.xpath("//input[@name='q']"));
		move.sendKeys("iphone13");

		WebElement click = driver.findElement(By.xpath("//button[@type='submit']"));
		click.click();

		WebElement next = driver.findElement(By.xpath("(//div[@class='_4WELSP'])[1]"));
		next.click();

		String parent = driver.getWindowHandle();

		Set<String> child = driver.getWindowHandles();

		for (String grand : child) {

			if (grand != parent) {
				driver.switchTo().window(grand);

			}
		}

	//	WebElement add = driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2 JTo6b7']"));
	//	add.click();

		JavascriptExecutor j = (JavascriptExecutor) driver;

		WebElement down = driver.findElement(By.xpath("//div[@class='WtiB86']"));
		j.executeScript("arguments[0].scrollIntoView(false)", down);
		
		Thread.sleep(3000);

		WebElement up = driver.findElement(By.xpath("//span[text()='Electronics']"));
		j.executeScript("arguments[0].scrollIntoView(true)", up);
		
		Actions a = new Actions(driver);

		WebElement serch = driver.findElement(By.xpath("//input[@class='zDPmFV']"));
		serch.click();
		serch.sendKeys("backcover");
		a.doubleClick(serch).perform();
		a.contextClick(serch).perform();

		Robot r = new Robot();

		for (int i = 0; i < 2; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		WebElement add = driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2 JTo6b7']"));
		add.click();
		
		WebElement re = driver.findElement(By.xpath("//input[@class='zDPmFV']"));
		a.contextClick(re).perform();


		for (int i = 0; i < 2; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		WebElement c = driver.findElement(By.xpath("//button[@class='MJG8Up']"));
		c.click();

		TakesScreenshot t = (TakesScreenshot) driver;
		Object tem = t.getScreenshotAs(OutputType.FILE);
		File per = new File("E:\\testing ecl\\FlipkartSelenium\\log.png");
	    FileUtils.copyFile((File) tem, per);
	}

}
