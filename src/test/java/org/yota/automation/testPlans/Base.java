package org.yota.automation.testPlans;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.yota.automation.browser.Browser;
import org.yota.automation.pages.HomePage;
import org.yota.automation.util.ExternalApp;

/**
 * Created by Denis on 5/7/2016.
 */
public class Base {

    protected HomePage hp;
    protected ExternalApp app;
    protected Browser br;

    @BeforeClass
    public void init() {
        app = new ExternalApp();
        app.startApp();
        br = new Browser();
        hp = br.openHomePage();
    }

    @AfterClass
    public void complete() {
        br.closeBrowser();
        app.stopApp();
    }

}
