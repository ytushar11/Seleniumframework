package pageObject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtenetReportNg {

	
	public static ExtentReports getReportObject() {
		String filepath = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
		reporter.config().setReportName("ExtentReportDemo");
		reporter.config().setDocumentTitle("InitialTest");
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Tushar");
		return report;
	}
}
