package com.amazon.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports extent;

	public static ExtentReports extentReportGenerator() {
		
		String path = System.getProperty("user.dir")+"\\extentreports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Automation Testing");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Sandeep", "Automation");
		return extent;
	}

}
