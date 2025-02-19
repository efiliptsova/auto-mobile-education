package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
  static {
    ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    ARTICLE_DESCRIPTION = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description']";
    ARTICLE_TITLE_ON_ARTICLE_PAGE = "id:org.wikipedia:id/view_page_title_text";
    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
    OPTIONS_ADD_TO_READING_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
    ADD_TO_READING_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
    READING_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
    READING_LIST_OK_BUTTON = "xpath://*[@text='OK']";
    READING_LIST_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']";
    CLOSE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
  }

  public AndroidArticlePageObject(AppiumDriver driver) {
    super(driver);
  }
}
