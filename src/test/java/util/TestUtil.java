package util;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import static org.openqa.selenium.Keys.TAB;

public class TestUtil {

	public static int PAGE_LOAD_TIMEOUT = 50;
	public static long IMPLICIT_WAIT = 50;
	static final String random = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();


	/**
	 * To generate random string
	 */
	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(random.charAt(rnd.nextInt(random.length())));
		return sb.toString();
	}


	//Method to capture the Screen shots at the end only when testcase is failed
	public static void takeScreenshotAtEndOfTest(WebDriver driver) throws Exception {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.Dir");
			//"MyfilePATH");
			FileUtils.copyFile(scrFile, new File("/screenshots/" + currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}


	public static void clickElement(WebElement element) throws InterruptedException {


		int seconds = PAGE_LOAD_TIMEOUT;
		long time = 1000 * seconds;
		boolean timeout = false;
		while (!timeout && time > 0) {
			try {
				element.click();

				timeout = true;
				Thread.sleep(100);
			} catch (Exception e) {
				timeout = false;
				Thread.sleep(100);
				time = time - 100;
			}
		}
	}


    public static int getRandomInt(int min, int max)
    {
        Random r = new Random();
        int randomNum = r.nextInt((max - min) + 1) + min;
        return randomNum;
    }


	public static void selectFromDropdownList(WebElement element, String value) throws InterruptedException {
		if(value!=null){
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(value);
			element.click();
		}
	}


	 public static void enterText(WebElement element, String value) throws InterruptedException
	 {
	 element.click();
	 String str=element.getText();
	 if(str!=null ) 
	 {
     element.clear();
	 }
		 if (value != null) {
			 element.sendKeys(value);
			 element.sendKeys(TAB);
		 }

	 }
	




			   
}
	
	

