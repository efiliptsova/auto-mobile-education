package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject{

  private static final String
          STEP_WELCOME_SCREEN1 = "id:Learn more about Wikipedia",
          NEXT_BTN = "id:Next",
          STEP_WELCOME_SCREEN2 = "id:New ways to explore",
          STEP_WELCOME_SCREEN3 = "id:Add or edit preferred languages",
          STEP_WELCOME_SCREEN4 = "id:Learn more about data collected",
          GET_STARTED_BTN = "id:Get started",
          SKIP_BTN = "id:Skip";

  public WelcomePageObject(AppiumDriver driver) {
    super(driver);
  }

  public void waitForWelcomeScreen1() {
    this.waitForElementPresent(STEP_WELCOME_SCREEN1, "Cannot find 'Learn more about Wikipedia' link", 10);
  }

  public void clickNext() {
    this.waitForElementAndClick(NEXT_BTN, "Cannot find and click 'Next' button", 10);
  }

  public void waitForWelcomeScreen2() {
    this.waitForElementPresent(STEP_WELCOME_SCREEN2, "Cannot find 'New ways to explore' link", 10);
  }

  public void waitForWelcomeScreen3() {
    this.waitForElementPresent(STEP_WELCOME_SCREEN3, "Cannot find 'Add or edit preferred languages' link", 10);
  }

  public void waitForWelcomeScreen4() {
    this.waitForElementPresent(STEP_WELCOME_SCREEN4, "Cannot find 'Learn more about data collected' link", 10);
  }

  public void clickGetStarted() {
    this.waitForElementAndClick(GET_STARTED_BTN, "Cannot find and click 'Get started' button", 10);
  }

  public void clickSkip() {
    this.waitForElementAndClick(SKIP_BTN, "Cannot find and click 'Skip' button", 10);
  }
}
