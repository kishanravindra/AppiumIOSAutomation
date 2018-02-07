package com.qwinix.awsdev.screens;



import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

public class LoginScreen{
	
	IOSDriver<WebElement> driver;

	@BeforeTest
	public void setup() throws MalformedURLException, InterruptedException {
		
		//File ipaBuild = new File("builds_ipa/DocDock.ipa");
		DesiredCapabilities capabilities = new DesiredCapabilities();
	   /* capabilities.setCapability("udid", "5613fb36482321818bb951f77507878de144feb5");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "11.2");
		capabilities.setCapability("bundleId","com.docdock.ios");
		capabilities.setCapability("deviceName", "iPhone");
		capabilities.setCapability("app", ipaBuild);
		
		capabilities.setCapability("bundleId","com.docdock.ios");*/
		driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		// Create an implicitly wait instance to define the timeout for 'findElement' commands.
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
		Alert notificationAlert = driver.switchTo().alert();
		notificationAlert.accept();
	}
	
	@Test 
	public void loginWithInvalidEmail() throws InterruptedException {
		System.out.println("Invalid Email called");
    	    WebDriverWait wait = new WebDriverWait(driver, 15);
    	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailField")));
		driver.findElementByAccessibilityId("emailField").sendKeys("kishan@");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordField")));
		driver.findElementByAccessibilityId("passwordField").sendKeys("password");
		driver.findElementByAccessibilityId("loginBtn").click();
		
		Boolean isalertDisplayed = driver.findElement(By.xpath("//XCUIElementTypeAlert[@name=\"Please enter valid Email Address\"]")).isDisplayed();
		Assert.assertTrue(isalertDisplayed);
		String actual= driver.findElement(By.xpath("//XCUIElementTypeAlert[@name=\"Please enter valid Email Address\"]")).getText();
		String expected = "Please enter valid Email Address";
		Thread.sleep(2000);
		Assert.assertEquals(expected, actual);
		Alert invalidAlert = driver.switchTo().alert();
		Thread.sleep(2000);
		invalidAlert.accept();
		Thread.sleep(1500);
		driver.findElementByAccessibilityId("emailField").clear();
		driver.findElementByAccessibilityId("passwordField").clear();
		Thread.sleep(2500);
	}
	
	   @Test 
		public void loginWithInvalidCredentials() throws InterruptedException {
	         System.out.println("InValid credndentials");
	     	 WebDriverWait wait = new WebDriverWait(driver, 15);
	    	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailField")));
			 driver.findElementByAccessibilityId("emailField").sendKeys("iosuser@yopmail.com");
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordField")));
			 driver.findElementByAccessibilityId("passwordField").sendKeys("passwordM1");
			 driver.findElementByAccessibilityId("loginBtn").click();
			 Thread.sleep(1500);
			
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Invalid Login")));
			
             Boolean isalertDisplayed = driver.findElementByAccessibilityId("Invalid Login").isDisplayed();
            
			Assert.assertTrue(isalertDisplayed);
			Thread.sleep(2500);
			Assert.assertEquals(driver.findElementByAccessibilityId("Invalid Login").getText(), "Invalid Login");
			Alert invalidAlert = driver.switchTo().alert();
			invalidAlert.accept();
			Thread.sleep(5000);
			driver.findElementByAccessibilityId("emailField").clear();
			driver.findElementByAccessibilityId("passwordField").clear();
			Thread.sleep(4000);
	}
	
	@Test 
	public void loginWithValidCredentialsAndInvalidPin() throws InterruptedException {
		System.out.println("Valid credndentials and invalid pin");
		Thread.sleep(1500);
		driver.findElementByAccessibilityId("emailField").sendKeys("rkishan@qwinix.io");
		driver.findElementByAccessibilityId("passwordField").sendKeys("passwordM@1");
		Thread.sleep(1500);
		driver.findElementByAccessibilityId("loginBtn").click();
		Thread.sleep(5000);
	     driver.findElementByAccessibilityId("passcode:0").sendKeys("1");
	     driver.findElementByAccessibilityId("passcode:1").sendKeys("2");
	     driver.findElementByAccessibilityId("passcode:2").sendKeys("3");
	     driver.findElementByAccessibilityId("passcode:3").sendKeys("4");
		Thread.sleep(2000);
	     driver.findElementByAccessibilityId("passcode:0").sendKeys("1");
	     driver.findElementByAccessibilityId("passcode:1").sendKeys("2");
	     driver.findElementByAccessibilityId("passcode:2").sendKeys("5");
	     driver.findElementByAccessibilityId("passcode:3").sendKeys("4");
	   
		Thread.sleep(4000);
		Assert.assertEquals("Passcodes did not match. Try again.", driver.findElementByAccessibilityId("Passcodes did not match. Try again.").getText());
		Thread.sleep(1500);
		driver.findElementByAccessibilityId("Cancel").click();
		Thread.sleep(2500);
	}
	
	   @Test 
		public void loginWithValidCredentialsAndPin() throws InterruptedException {
			System.out.println("Valid credendentials and  pin");
			Thread.sleep(1500);
			driver.findElementByAccessibilityId("emailField").sendKeys("rkishan@qwinix.io");
			driver.findElementByAccessibilityId("passwordField").sendKeys("passwordM@1");
			driver.findElementByAccessibilityId("loginBtn").click();
			Thread.sleep(3000);
		     driver.findElementByAccessibilityId("passcode:0").sendKeys("1");
		     driver.findElementByAccessibilityId("passcode:1").sendKeys("2");
		     driver.findElementByAccessibilityId("passcode:2").sendKeys("3");
		     driver.findElementByAccessibilityId("passcode:3").sendKeys("4");
			Thread.sleep(2000);
		     driver.findElementByAccessibilityId("passcode:0").sendKeys("1");
		     driver.findElementByAccessibilityId("passcode:1").sendKeys("2");
		     driver.findElementByAccessibilityId("passcode:2").sendKeys("3");
		     driver.findElementByAccessibilityId("passcode:3").sendKeys("4");

			Thread.sleep(5000);
			Assert.assertEquals("Colorado Preventive Medicine", driver.findElementByAccessibilityId("Colorado Preventive Medicine").getText());
			System.out.println("Login Successfull");	
			//Click on settings btn
			Thread.sleep(1500);
			driver.findElementByAccessibilityId("Icon Settings").click();
			Thread.sleep(1500);
			//Click on Logout btn
		    driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"maroon btn\"])[3]")).click();
			Thread.sleep(2500);
			//Accepting alert
		    Alert acceptAlert = driver.switchTo().alert();
		    acceptAlert.accept();
			Thread.sleep(3000);
		    Boolean isfieldFieldExist = driver.findElementByAccessibilityId("emailField").isDisplayed();
		    Assert.assertTrue(isfieldFieldExist);
			Thread.sleep(2000);
		}
		
		@Test  
		public void testForgotAction() throws InterruptedException {
	        System.out.println("Forgot action");
	        Thread.sleep(2000);
			driver.findElementByAccessibilityId("forgotBtn").click();
			Thread.sleep(2000);
			Boolean isalertDisplayed = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Forgot Your Password?\"]")).isDisplayed();
			Assert.assertTrue(isalertDisplayed);
			Assert.assertEquals(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Forgot Your Password?\"]")).getText(), "Forgot Your Password?");
			Alert invalidAlert = driver.switchTo().alert();
			invalidAlert.dismiss();
		}
		
	//@Test
	 public void testSignUpAction() throws InterruptedException {
	        System.out.println("Signup action");
			driver.findElementByAccessibilityId("signupBtn").click();
			Boolean btnSignUp = driver.findElementByAccessibilityId("signupRegisterBtn").isDisplayed();
			Assert.assertTrue(btnSignUp);
			System.out.println("SignUp screen visible");	 
			driver.findElementByAccessibilityId("fNameField").sendKeys("Qwinix2");
			driver.findElementByAccessibilityId("lNameField").sendKeys("QA2");
			driver.findElementByAccessibilityId("emailField").sendKeys("qwinix134@yopmail.com");
			driver.findElementByAccessibilityId("passwordField").click();

			Alert acceptAlert = driver.switchTo().alert();
		    acceptAlert.accept();
			driver.findElementByAccessibilityId("passwordField").sendKeys("Qwinix@12343");;

     		driver.findElementByAccessibilityId("confirmField").sendKeys("Qwinix@12343");
			driver.findElementByAccessibilityId("Done").click();
			
			Assert.assertEquals(driver.findElementByAccessibilityId("Account has been created successfully").getText(), "Account has been created successfully");
			
			Alert okAlert = driver.switchTo().alert();
			okAlert.accept();
            Boolean isfieldFieldExist = driver.findElementByAccessibilityId("emailField").isDisplayed();
			Assert.assertTrue(isfieldFieldExist);
		}
	

	
	
	@AfterTest
	public void teardown() throws InterruptedException {
		driver.quit();
	}
}
