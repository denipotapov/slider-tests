package org.yota.automation.testPlans;

import org.testng.Assert;
import org.testng.annotations.*;
import org.yota.automation.offer.Offer;
import org.openqa.selenium.StaleElementReferenceException;
import org.yota.automation.pages.UnavailableElementException;

public class AutoSmokeTests extends Base {

  private double bal;
  private Offer offer;

  @BeforeClass
  public void init() {
    super.init();
    bal = hp.getBalance();
    offer = hp.getCurrentOffer();
  }

  @AfterClass
  public void complete() {
    hp.reset();
    super.complete();
  }

  @Test (groups = "smoke", description = "It's possible to increase integer value")
  public void increaseBalanceWithInteger() {
    hp.reset();
    bal = hp.getBalance();
    hp.topUpAmount(1000);
    bal+= 1000;
    Assert.assertEquals(hp.getBalance(), bal, "Not equal balance.");
  }

  @Test (groups = "smoke", description = "It's possible to increase floating point value")
  public void increaseBalanceWithFractional(){
    hp.reset();
    bal = hp.getBalance();
    hp.topUpAmount(0.05);
    bal+= 0.05;
    Assert.assertEquals(hp.getBalance(), bal, "Not equal balance.");
  }

  @Test (groups = "smoke", description = "It's impossible to make balance negative")
  public void doNegativePayment() {
    hp.reset();
    bal = hp.getBalance();
    hp.topUpAmount(-1);
    Assert.assertEquals(hp.getBalance(), bal, "Not allowed to do negative payments.");
  }

  @Test (groups = "smoke", description = "Buy offer with enough money")
  public void buyOffer() throws UnavailableElementException{
    hp.reset();
    hp.topUpAmount(1000);
    hp.moveSliderRight(1);
    offer = hp.getNewOffer();
    hp.buyOffer();
    bal = hp.getBalance();
    Assert.assertTrue(offer.equals(hp.getCurrentOffer()), "Not equal offers after purchase.");
    Assert.assertEquals(bal, 1000d - offer.getCost(), "Balance error after offer purchase.");
  }

  @Test (groups = "smoke", description = "Change paid offer to another paid")
  public void changeOffer() throws UnavailableElementException{
    hp.reset();
    hp.topUpAmount(1000);
    hp.moveSliderRight(1);
    hp.buyOffer();
    hp.moveSliderRight(1);
    hp.buyOffer();
    offer = hp.getNewOffer();
    Assert.assertTrue(offer.equals(hp.getCurrentOffer()), "Not equal offers after change.");
  }

  @Test (groups = "smoke", description = "Offer will be set to default free after page refresh")
  public void checkOfferConditionsAfterPageRefresh() throws UnavailableElementException {
    hp.reset();
    hp.topUpAmount(1000);
    hp.moveSliderRight(2);
    hp.buyOffer();
    br.refresh();
    Assert.assertTrue(new Offer(0, "64", 0).equals(hp.getCurrentOffer()), "Not equal offers after page refresh.");
  }

  @Test (groups = "smoke",
          description = "It's impossible to buy offer with not enough money",
          expectedExceptions = UnavailableElementException.class)
  public void tryToByOfferWithNoMoney() throws UnavailableElementException {
    hp.reset();
    hp.moveSliderRight(5);
    offer = hp.getNewOffer();
    bal = hp.getBalance();
    try {
      hp.buyOffer();
      Assert.fail("Impossible to buy offer without money");
    } catch (UnavailableElementException e) {
      throw new UnavailableElementException(e.getMessage());
    }
  }
}
