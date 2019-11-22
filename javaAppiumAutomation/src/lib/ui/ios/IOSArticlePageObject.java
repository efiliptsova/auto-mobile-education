package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

  static {
    ARTICLE_TITLE = "id:Java (programming language)";
    ARTICLE_DESCRIPTION = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description']"; //
    //ARTICLE_TITLE_ON_ARTICLE_PAGE = "xpath://XCUIElementTypeWebView"; //
    ARTICLE_TITLE_ON_ARTICLE_PAGE = "id:JavaScript";
    //OPTIONS_ADD_TO_READING_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
    OPTIONS_ADD_TO_READING_LIST_BUTTON = "id:Save for later";
    //ADD_TO_LIST_CONFIRM_BUTTON = "id:add-to-list";
    //xpath://XCUIElementTypeImage[@name="add-to-list"]
    ADD_TO_LIST_CONFIRM_BUTTON = "xpath://XCUIElementTypeImage[@name='add-to-list']";
    READING_LIST_NAME_INPUT = "xpath://XCUIElementTypeTextField[@value='reading list title']";
    READING_LIST_OK_BUTTON = "id:Create reading list";
    READING_LIST_CREATE_BUTTON = "id:Add";
    READING_LIST_NAME_TPL = "id:{FOLDER_NAME}";
    CLOSE_BUTTON = "id:Back";
  }

  public IOSArticlePageObject(AppiumDriver driver) {
    super(driver);
  }


}
