package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

  static {
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
    SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
    SEARCH_RESULT = "id:org.wikipedia:id/page_list_item_container";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    SEARCH_CANCEL_BTN = "id:org.wikipedia:id/search_close_btn";
  }

  public IOSSearchPageObject(AppiumDriver driver) {
    super(driver);
  }
}
