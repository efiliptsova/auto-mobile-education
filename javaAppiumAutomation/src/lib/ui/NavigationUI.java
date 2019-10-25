package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends  MainPageObject{

  private static final String MY_LIST_BUTTON = "//android.widget.FrameLayout[@content-desc='My lists']";

  public NavigationUI(AppiumDriver driver) {
    super(driver);
  }

  public void goToMyList() {
    waitForElementAndClick(
            By.xpath(MY_LIST_BUTTON),
            "Cannot find navigation button to My List",
            15);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
