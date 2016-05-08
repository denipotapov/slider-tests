package org.yota.automation.pages;

import org.openqa.selenium.WebDriver;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

  protected WebDriver driver;

  public Page(WebDriver driver) {
    this.driver = driver;
  }


}
