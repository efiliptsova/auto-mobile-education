package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{

  private static final String
          WELCOME_SCREEN1 = "id:Learn more about Wikipedia";
  WELCOME_SCREEN2 = "id:New ways to explore";
  WELCOME_SCREEN3 = "id:Add or edit preferred languages";
  WELCOME_SCREEN4 = "Learn more about data collected";
  GET_STARTED = "id:Get started";

  public WelcomePageObject(AppiumDriver driver) {
    super(driver);
  }

  public void waitForWelcomeScreen1() {
    this.waitForElementAndClick(WELCOME_SCREEN1, "Connot find 'Learn more about Wikipedia'", 10);
  }

    public void clickNext() {
        this.waitForElementAndClick("id:Next", "Connot find 'Learn more about Wikipedia'", 10);
    }


}
