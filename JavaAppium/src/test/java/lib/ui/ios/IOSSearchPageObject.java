package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

  static {
    SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
    SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
    SEARCH_RESULT = "xpath://XCUIElementTypeLink";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
    SEARCH_CANCEL_BTN = "id:Close";
  }

  public IOSSearchPageObject(AppiumDriver driver) {
    super(driver);
  }
}
