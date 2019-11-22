package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {

  static {
    LIST_NAME_TPL = "xpath://android.widget.TextView[@text='{LIST_NAME}']";
            ARTICLE_NAME_IN_LIST_TPL = "xpath://*[@text='{ARTICLE_NAME}']";
  }

  public AndroidMyListPageObject(AppiumDriver driver) {
    super(driver);
  }
}
