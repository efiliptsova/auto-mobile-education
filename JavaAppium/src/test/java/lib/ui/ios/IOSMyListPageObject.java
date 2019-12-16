package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOSMyListPageObject extends MyListPageObject {
  static {
    LIST_NAME_TPL = "";
    ARTICLE_NAME_IN_LIST_TPL = "xpath://XCUIElementTypeLink[contains(@name='{TITLE}')]";
  }

  public IOSMyListPageObject(AppiumDriver driver) {
    super(driver);
  }
}
