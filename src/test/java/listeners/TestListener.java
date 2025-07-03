package listeners;

import com.aventstack.extentreports.Status;
import driver.DriverManager;
import helpers.CaptureHelper;
import helpers.PropertiesHelper;
import io.qameta.allure.Attachment;
import keywords.WebUI;
import reports.AllureManager;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.LogUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static int test_total;
    private static int test_passed_total;
    private static int test_failed_total;
    private static int test_skipped_total;
    //C:\allure-2.34.1\bin\allure.bat serve target/allure-results
    
    public String getTestName(ITestResult result) {
        return result.getMethod().getMethodName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ?
                result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext context) {
        LogUtils.info("⚙️ Start Suite: " + context.getName());
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtils.info("✅ Finished Suite: " + context.getName());
        LogUtils.info("⭐ Total: " + test_total + ", Passed: " + test_passed_total +
                ", Failed: " + test_failed_total + ", Skipped: " + test_skipped_total);
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = getTestName(result);
        test_total++;
        LogUtils.info("▶️ Start test: " + testName);
        ExtentTestManager.saveToReport(testName, getTestDescription(result));
        CaptureHelper.startRecord(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = getTestName(result);
        LogUtils.info("✅ Passed: " + testName);
        ExtentTestManager.logMessage(Status.PASS, "Test passed");
        test_passed_total++;
        CaptureHelper.stopRecord(testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = getTestName(result);
        LogUtils.error("❌ Failed: " + testName);
        LogUtils.error(result.getThrowable());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.addScreenshot(testName);
        AllureManager.saveScreenshotPNG();
        test_failed_total++;
        CaptureHelper.captureScreenshot(testName);
        CaptureHelper.stopRecord(testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = getTestName(result);
        LogUtils.warn("⚠️ Skipped: " + testName);
        ExtentTestManager.logMessage(Status.SKIP, testName + " was skipped.");
        test_skipped_total++;
        CaptureHelper.stopRecord(testName);
    }
}
