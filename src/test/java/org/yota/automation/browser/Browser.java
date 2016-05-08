package org.yota.automation.browser;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.yota.automation.pages.HomePage;


/**
 * Class implements browser driver interface.
 */
public class Browser {


    private WebDriver webDriver;

    public Browser () {
        webDriver = new FirefoxDriver();
    }

    public void closeBrowser() {
        webDriver.quit();
    }

    public HomePage openHomePage() {
        HomePage hp = new HomePage(webDriver);
        webDriver.get("http://localhost:4567");
        return hp;
    }

    public void refresh(){
        webDriver.navigate().refresh();
    }

    public void get(String URL) {
        webDriver.get(URL);
    }


}
