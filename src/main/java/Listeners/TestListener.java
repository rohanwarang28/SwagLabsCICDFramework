package Listeners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.pages.BasePage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportHelper;

import java.time.LocalDateTime;


public class TestListener implements ITestListener {
    public static final ExtentReports reports = ExtentReportHelper.getReportObject();
    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        test = reports.createTest(result.getMethod().getMethodName());
        testLogger.set(test);
        testLogger.get().log(Status.INFO,"Driver start time : "+LocalDateTime.now());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testLogger.get().generateLog(Status.FAIL,"Test "+result.getMethod().getMethodName()+" Failed");
        testLogger.get().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.takeScreenShot()).build());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testLogger.get().generateLog(Status.PASS,"Test "+result.getMethod().getMethodName()+" Passed");
    }

    @Override
    public void onFinish(ITestContext context) {
       reports.flush();
    }
}
