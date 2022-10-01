package com.amazon.qa.util;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amazon.qa.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Listeners extends TestBase implements ITestListener {

	public Listeners() throws IOException {
		super();
	}

	ExtentReports extent = ExtentReportNG.extentReportGenerator();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Case Success");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		extentTest.get().fail(result.getThrowable());

		Object obj = result.getInstance();
		Class attributes = result.getTestClass().getRealClass();
		try {
			driver = (WebDriver) attributes.getField("driver").get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		 try {
		 extentTest.get().addScreenCaptureFromPath(takeScreenshot(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName()); 
		 } 
		 catch(IOException e) { 
			 e.printStackTrace(); 
		 }
		 

//		try {
//			extentTest.get().fail(result.getThrowable(), MediaEntityBuilder
//					.createScreenCaptureFromPath(takeScreenshot(result.getMethod().getMethodName(), driver)).build());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
