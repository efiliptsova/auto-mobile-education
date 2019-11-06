package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends  MainPageObject{

  protected static String MY_LIST_BUTTON;

  public NavigationUI(AppiumDriver driver) {
    super(driver);
  }

  public void goToMyList() {
    waitForElementAndClick(
            MY_LIST_BUTTON,
            "Cannot find navigation button to My List",
            15);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
