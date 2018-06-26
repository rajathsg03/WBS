package org.wbs.wholesale;

import org.wbs.wholesale.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.wbs.wholesale.ddf.util.ExtentManager;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	


	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	public WebDriver driver;
	Properties properties;
	
	
/*****************************Open Browser********************************/
	
	public void openbrowser(String browser)
	{
		
		if(browser.equals("Mozilla"))
		{
			driver=new FirefoxDriver();
		} else if (browser.equals("Chrome")) {
			driver=new ChromeDriver();
			//test.log(LogStatus.INFO, "Browser is launched");
		}
		
	}

	
/*****************************Reporting********************************/
	
	public void reportPass(String msg){
		test.log(LogStatus.PASS, msg);
	}
	
	public void reportFailure(String msg){
		test.log(LogStatus.FAIL, msg);
		takeScreenShot();
		Assert.fail(msg);
	}
	
	public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//ScreenShots//"+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catcsh block
			e.printStackTrace();
		}
		//put screenshot file in reports
		test.log(LogStatus.INFO,"Screenshot-> "+ test.addScreenCapture(System.getProperty("user.dir")+"//ScreenShots//"+screenshotFile));
		
	}

/*fetch data*/
	

	
	public BaseTest(String FilePath){
	
	try{
		System.out.println("inside the super class constructor");
	FileInputStream file_path= new FileInputStream(FilePath);
	properties = new Properties();
	properties.load(file_path);
	}
	catch(FileNotFoundException e)
	{
		e.printStackTrace();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	
	}
	
	
	public String getData(String ElementName)
	{
		String data=properties.getProperty(ElementName);
		return data;
	}
	
	public By getLocator(String ElementName) throws Exception
	{
		String Locator=properties.getProperty(ElementName);
		String locatorType=Locator.split(":")[0];
		String locatorValue=Locator.split(":")[1];
		
		if(locatorType.toLowerCase().equals("id"))
		{
			return By.id(locatorValue);
		}else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue);
		}else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("Locator type '" + locatorType
					+ "' not defined!!");
		
	}
	
	
	
	

}
