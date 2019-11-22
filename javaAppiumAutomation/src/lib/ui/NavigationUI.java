package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class NavigationUI extends  MainPageObject{

  protected static String MY_LIST_BUTTON;

  public NavigationUI(AppiumDriver driver) {
    super(driver);
  }

  public void goToMyList() {
    WebElement el = waitForElementPresent(MY_LIST_BUTTON, "Cannot find element 'My List'", 10);
    // получить самую левую точку элемента по оси х
    int left_x = el.getLocation().getX();
    // получить самую правую точку элемента по оси х
    int right_x = left_x + el.getSize().getWidth();

    waitForElementAndClick(
            MY_LIST_BUTTON,
            "Cannot find navigation button to My List",
            15);
    // закрываем всплывающее окно
    if (Platform.getInstance().isIOS())
    {
      TouchAction action = new TouchAction(driver);
      action.tap(left_x, right_x).perform();
    }
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
