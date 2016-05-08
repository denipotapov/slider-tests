package org.yota.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.yota.automation.offer.Offer;

/**
 * Home page actions and elements
 */
public class HomePage extends Page {

  private final String SLIDER_DECREASE_XPATH = "//a[@data-bind='click: moveLeft']";
  private final String SLIDER_INCREASE_XPATH = "//a[@data-bind='click: moveRight']";
  private final String ADD_OFFER_XPATH = "//a[@data-bind=' css: addOferButtonClasses, click: doPurchase']";
  private final String DO_PAYMENT_XPATH = "//a[@data-bind='click: doPayment']";
  private final String DO_RESET_XPATH = "//a[@data-bind='click: doReset']";
  private final String FILL_IN_AMOUNT_XPATH = "//input[@data-bind='value: amount']";
  private final String BALANCE_XPATH = "//span[@data-bind='text: balance']";
  private final String CURRENT_OFFER_DAYS_LEFT_XPATH = "//div[@class='tariff unavailable']//div[@class='time']//strong";
  private final String CURRENT_OFFER_SPEED_XPATH = "//div[@class='tariff unavailable']//strong[@data-bind='text: previousSpeed']";
  private final String CURRENT_OFFER_PRICE_XPATH = "//div[@class='tariff unavailable']//strong[@data-bind='text: previousCost']";
  private final String NEW_OFFER_DAYS_LEFT_XPATH = "//div[@class='main-offer']//div[@class='time']//strong";
  private final String NEW_OFFER_SPEED_XPATH = "//div[@class='main-offer']//strong[@data-bind='text: currentSpeed']";
  private final String NEW_OFFER_PRICE_XPATH = "//div[@class='main-offer']//strong[@data-bind='text: currentCost']";

  private WebElement element;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }

  public double getBalance(){
    element = driver.findElement(By.xpath(BALANCE_XPATH));
    return Double.parseDouble(element.getText());
  }

  public void moveSliderLeft(int step){
    for (int i = 0 ; i < step; i++)
      driver.findElement(By.xpath(SLIDER_DECREASE_XPATH)).click();
  }

  public void moveSliderRight(int step){
    for (int i = 0 ; i < step; i++)
      driver.findElement(By.xpath(SLIDER_INCREASE_XPATH)).click();
  }

  public void buyOffer() throws UnavailableElementException{
    element = driver.findElement(By.xpath(ADD_OFFER_XPATH));

    if (element.getAttribute("class").equals("btn"))
      element.click();
    else
      throw new UnavailableElementException("Purchase button is unavailable");
    try {
      Thread.sleep(1000);
    }
    catch(InterruptedException e) {}
  }

  public void topUpAmount(double amount) {
    element = driver.findElement(By.xpath(FILL_IN_AMOUNT_XPATH));
    element.clear();
    element.sendKeys(String.valueOf(amount));
    element.findElement(By.xpath(DO_PAYMENT_XPATH)).click();

    try {
      Thread.sleep(1000);
    }
    catch(InterruptedException e) {}
  }

  public void reset(){
    driver.findElement(By.xpath(DO_RESET_XPATH)).click();
    try {
      Thread.sleep(1000);
    }
    catch(InterruptedException e) {}
  }
  public Offer getCurrentOffer(){
    Offer curOffer = new Offer(Integer.parseInt(driver.findElement(By.xpath(CURRENT_OFFER_DAYS_LEFT_XPATH)).getText()),
                               driver.findElement(By.xpath(CURRENT_OFFER_SPEED_XPATH)).getText(),
                               Integer.parseInt(driver.findElement(By.xpath(CURRENT_OFFER_PRICE_XPATH)).getText()));
    return curOffer;
  }
  public Offer getNewOffer(){
    Offer newOffer = new Offer(Integer.parseInt(driver.findElement(By.xpath(NEW_OFFER_DAYS_LEFT_XPATH)).getText()),
                              driver.findElement(By.xpath(NEW_OFFER_SPEED_XPATH)).getText(),
                              Integer.parseInt(driver.findElement(By.xpath(NEW_OFFER_PRICE_XPATH)).getText()));
    return newOffer;
  }

}
