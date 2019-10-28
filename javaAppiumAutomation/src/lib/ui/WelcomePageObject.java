package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{

  private static final String
          WELCOME_SCREEN1 = "id:Learn more about Wikipedia";

  public WelcomePageObject(AppiumDriver driver) {
    super(driver);
  }

  public void waitForWelcomeScreen1() {
    this.waitForElementAndClick(WELCOME_SCREEN1, "Connot find 'Learn more about Wikipedia'", 10);
  }
}
