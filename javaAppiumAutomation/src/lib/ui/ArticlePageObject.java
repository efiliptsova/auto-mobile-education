package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

  private static final String
          ARTICLE_TITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
          ARTICLE_DESCRIPTION = "//*[@resource-id='org.wikipedia:id/page_list_item_description']",
          ARTICLE_TITLE_ON_ARTICLE_PAGE = "org.wikipedia:id/view_page_title_text",
          OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
          OPTIONS_ADD_TO_READING_LIST_BUTTON = "//*[@text='Add to reading list']",
          ADD_TO_READING_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
          READING_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
          READING_LIST_OK_BUTTON = "//*[@text='OK']",
          READING_LIST_NAME_TPL = "//android.widget.TextView[@text='{FOLDER_NAME}']",
          CLOSE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

  public ArticlePageObject(AppiumDriver driver) {
    super(driver);
  }

  /*TEMPLATE_METHODs*/
  private static String getReadingListName(String substring) {
    return READING_LIST_NAME_TPL.replace("{FOLDER_NAME}", substring);
  }
  /*TEMPLATE_METHODs*/

  public String getArticleTitle(WebElement el) {
    return el.findElement(By.xpath(ARTICLE_TITLE)).getText();
  }

  public String getArticleDescription(WebElement el) {
    return el.findElement(By.xpath(ARTICLE_DESCRIPTION)).getText();
  }

  public void waitForArticleLoaded() {
    waitForElementsPresent(
            By.id(ARTICLE_TITLE_ON_ARTICLE_PAGE),
            "Cannot find article title",
            15);
  }

  public String getArticleTitle() {
    WebElement el = waitForElementPresent(By.id(ARTICLE_TITLE_ON_ARTICLE_PAGE), "Cannot find article title", 10);
    return el.getText();
  }

  public void clickOptionButton() {
    waitForElementAndClick(
            By.xpath(OPTIONS_BUTTON),
            "Cannot find button to open article options",
            5);
  }

  public void clickAddToReadingListButton() {
    waitForElementAndClick(
            By.xpath(OPTIONS_ADD_TO_READING_LIST_BUTTON),
            "Cannot find option to add article to reading list",
            5);
  }

  public void clickGotItOverlay() {
    waitForElementAndClick(
            By.id(ADD_TO_READING_LIST_OVERLAY),
            "Cannot find 'Got it' tip overlay",
            5);
  }

  public void enterListNameAndPressOk(String folderName) {
    waitForElementAndClear(
            By.id(READING_LIST_NAME_INPUT),
            "Cannot clear text into articles folder input",
            5);
    waitForElementAndSendKeys(
            By.id(READING_LIST_NAME_INPUT),
            folderName,
            "Cannot put text into articles folder input",
            5);
    waitForElementAndClick(
            By.xpath(READING_LIST_OK_BUTTON),
            "Cannot press 'OK' button",
            2);
  }

  public void selectReadingListByName(String folderName) {
    waitForElementAndClick(
            By.xpath(getReadingListName(folderName)),
            "Cannot find reading list with name" + folderName,
            5);
  }

  public void closeArticle() {
    waitForElementAndClick(
            By.xpath(CLOSE_BUTTON),
            "Cannot close article, cannot find X link",
            2);
  }

  // Сохраняет статью articleName в папку folderName
  public void saveArticle(String searchText, boolean newFolder, String articleName, String folderName) {

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchText);
    // Убеждаемся, что найдены статьи и выбираем статью с именем articleName
    searchPageObject.waitForSearchResultAndSelectArticle(articleName);
    waitForArticleLoaded();
    clickOptionButton();
    clickAddToReadingListButton();
    // если содаем новую папку
    if (newFolder)
    {
      clickGotItOverlay();
      enterListNameAndPressOk(folderName);
    }
    // если добавляем статью в уже существующую папку
    else
    {
      selectReadingListByName(folderName);
    }
    // закрытие статьи
    closeArticle();
  }

  // Проверяет, что заголовок статьи загрузился
  public void checkArticleTitleLoaded()
  {
    assertElementPresent(By.id(ARTICLE_TITLE_ON_ARTICLE_PAGE));
  }





}
