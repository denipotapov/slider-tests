package org.yota.automation.listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Denis on 5/8/2016.
 */
public class ReportListener implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> var1, List<ISuite> var2, String var3){

        System.out.println("=================================================");

        ISuite suite = var2.get(0);
        Map<String, Collection<ITestNGMethod>> methodsByGroup = suite.getMethodsByGroups();
        Map<String, ISuiteResult> tests = suite.getResults();
        for (String key : tests.keySet()) {
            System.out.println("Key: " + key + ", Value: " + tests.get(key));
        }
        Collection<ISuiteResult> suiteResults = tests.values();
        ISuiteResult suiteResult = suiteResults.iterator().next();
        ITestContext testContext = suiteResult.getTestContext();
        Collection<ITestNGMethod> perfMethods = methodsByGroup.get("smoke");
        IResultMap failedTests = testContext.getFailedTests();
        for (ITestNGMethod perfMethod : perfMethods) {
            Set<ITestResult> testResultSet = failedTests.getResults(perfMethod);
            for (ITestResult testResult : testResultSet) {
                System.out.println("Test " + testResult.getName() + " failed, error " + testResult.getThrowable());
            }
        }

        }
}
