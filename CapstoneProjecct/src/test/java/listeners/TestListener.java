package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotManager;

import java.io.IOException;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        try {
            extent = ExtentManager.getReporter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        testThread.get().log(Status.PASS, "✅ Test Passed: " + methodName);

        String path = ScreenshotManager.takeScreenshot(methodName + "_PASS");
        if (path != null) testThread.get().addScreenCaptureFromPath(path, methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        testThread.get().log(Status.FAIL, "❌ Test Failed: " + methodName);
        testThread.get().log(Status.FAIL, result.getThrowable());

        String path = ScreenshotManager.takeScreenshot(methodName + "_FAIL");
        if (path != null) testThread.get().addScreenCaptureFromPath(path, methodName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getMethod().getMethodName());
    }
}
