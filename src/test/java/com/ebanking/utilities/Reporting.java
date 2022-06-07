package com.ebanking.utilities;

//Listener Class used to generate extent reports

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    ReadConfig readConfig = new ReadConfig();
    String home = readConfig.getHomePath();

    public ExtentSparkReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    public void onStart(ITestContext testContext){

        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timestamp
        String repName = "Test-Report-"+timestamp+".html";
        htmlReporter = new ExtentSparkReporter(home + "/test-output/" + repName);
        try {
            htmlReporter.loadXMLConfig(home + "/extent-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            htmlReporter.loadXMLConfig(System.getProperty(home) + "/extent-config.xml");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        extentReports = new ExtentReports();

        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Hostname", "localhost");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("user","sivajyothi");

        htmlReporter.config().setDocumentTitle("E-Banking Test Project");
        htmlReporter.config().setReportName("Functional Test Report");
        //htmlReporter.config().(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

    }

    public void onTestSuccess(ITestResult testResult){
        extentTest = extentReports.createTest(testResult.getName());
        extentTest.log(Status.PASS, MarkupHelper.createLabel(testResult.getName(), ExtentColor.GREEN));

    }

    public void onTestFailure(ITestResult testResult) {
        extentTest = extentReports.createTest(testResult.getName());
        extentTest.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName(), ExtentColor.RED));
        String screenshotPath = home + "/Screenshots/" + testResult.getName() + ".png";

        File f = new File(screenshotPath);

        if (f.exists()) {
            try {
                extentTest.fail("Screenshot is below: " + extentTest.addScreenCaptureFromPath(screenshotPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        public void onTestSkipped(ITestResult testResult){
            extentTest = extentReports.createTest(testResult.getName());
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName(), ExtentColor.ORANGE));
        }



    public void onFinish(ITestContext testContext){
            extentReports.flush();
    }

}
