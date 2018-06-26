package org.wbs.wholesale;

import org.wbs.wholesale.*;
import org.wbs.wholesale.ConnectToDB.*;
import org.wbs.wholesale.ddf.util.ExtentManager;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login_with_valid_credentials extends BaseTest {

	public Login_with_valid_credentials() {
		
		super("ff");
		System.out.println("inside the sub class constructor");
		// TODO Auto-generated constructor stub
	}
	//public WebDriver driver;
	public BaseTest uimap;
	public BaseTest uilocator;
	WebDriverWait wait;
	ExtentReports rep=ExtentManager.getInstance();
	ExtentTest test;
	String ff="D:\\WBS_5.0\\src\\test\\TestData\\System_Details.properties";
	
	@BeforeTest
	public void connectiongToDb()
	{
		//ConnectToDB cdb=new ConnectToDB();
		
	}

	
	@Test
	
	public void loginToSystem() throws Exception
	{
		test=rep.startTest("Login_with_valid_credentials");
		test.log(LogStatus.INFO, "Starting the test");
		
		//String browser="Chrome";
		
		openbrowser("Chrome");
		uimap= new BaseTest("D:\\WBS_5.0\\src\\test\\TestData\\System_Details.properties");
		uilocator= new BaseTest("D:\\WBS_5.0\\src\\test\\ObjectRepository\\Login_xpath_locators.properties");
		driver.get((uimap.getData("URL_of_the_application")));
		test.log(LogStatus.INFO, "Application opened");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(uilocator.getLocator("UserName_textbox_ID")));
		driver.findElement(uilocator.getLocator("UserName_textbox_ID")).sendKeys(uimap.getData("NTUserName"));
		driver.findElement(uilocator.getLocator("Password_textbox_ID")).sendKeys(uimap.getData("NTPassword"));
		driver.findElement(uilocator.getLocator("SIGN_IN_button_ID")).click();
		//test.log(LogStatus.PASS, test.addScreenCapture("C:\\Users\\thukapr\\workspace_selenium\\WBS_5.0\\ScreenShots\\test1.png"));
		takeScreenShot();
		wait.until(ExpectedConditions.textToBe(uilocator.getLocator("Interconnect_Agreement_Type_locator"), "Interconnect"));
		//driver.wait(20);
		test.log(LogStatus.PASS, "Test Case executed successfully");
		driver.quit();

		
	}	
	 @AfterMethod
	 public void quit()
	 {
		 rep.endTest(test);
		 rep.flush();
	 }
		
		

	

}
