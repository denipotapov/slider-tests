package org.yota.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by Denis on 5/8/2016.
 */
public class TestListener implements ITestListener{

    public void onTestStart(ITestResult var1) {}

    public void onTestSuccess(ITestResult var1) {}

    public void onTestFailure(ITestResult var1) {
        log("Test " + var1.getName().toString() + " FAILED");
    }

    public void onTestSkipped(ITestResult var1) {}

    public void onTestFailedButWithinSuccessPercentage(ITestResult var1) {}

    public void onStart(ITestContext var1) {}

    public void onFinish(ITestContext var1) {}

    private void log (String par) {
        System.out.println(par);
    }

}
