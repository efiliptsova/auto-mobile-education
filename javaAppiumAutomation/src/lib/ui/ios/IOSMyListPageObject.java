package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;

public class IOSMyListPageObject extends MyListPageObject {

  static {
    LIST_NAME_TPL = "id:{LIST_NAME}";
    // "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
    ARTICLE_NAME_IN_LIST_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{ARTICLE_NAME}')]";
    READING_LIST_TAB = "id:Reading lists";
  }

  public IOSMyListPageObject(AppiumDriver driver) {
    super(driver);
  }
}
