package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject{

  private static final String
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
    SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
    SEARCH_RESULT = "id:org.wikipedia:id/page_list_item_container",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
    SEARCH_CANCEL_BTN = "id:org.wikipedia:id/search_close_btn";

  public SearchPageObject(AppiumDriver driver) {
    super(driver);
  }

  public void initSearchInput()
  {
    this.waitForElementAndClick(
            SEARCH_INIT_ELEMENT,
            "Cannot find 'Search Wikipedia' input",
            2);
    this.waitForElementPresent(
            SEARCH_INIT_ELEMENT,
            "Cannot find search input after clicking search init element",
            2);
  }

  public void typeSearchLine(String searchText)
  {
    this.waitForElementAndSendKeys(
            SEARCH_INPUT,
            searchText,
            "Cannot find and type into search input",
            2);
  }

  public String getSearchLineText()
  {
    WebElement searchField = this.waitForElementPresent(
            SEARCH_INPUT,
            "Cannot find search input",
            5);
    return searchField.getText();
  }

  /*TEMPLATE_METHODs*/
  private static String getResultSearchElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
  }
  /*TEMPLATE_METHODs*/

  public void waitForSearchResultAndSelectArticle(String substring) {
    String searchResultXpath = getResultSearchElement(substring);
    // Убеждаемся, что найдено несколько статей
    this.waitForElementAndClick(
            searchResultXpath,
            "Cannot find search result with substring " + substring,
            2);
  }

  // Убеждаемся, что найдены статьи
  public void waitForSearchResult() {
    this.waitForElementsPresent(
            SEARCH_RESULT,
            "Cannot find articles",
            2);
  }

  // Убеждаемся, что результат поиска пропал
  public void waitForEmptySearchResult() {
    this.waitForElementNotPresent(
            SEARCH_RESULT,
            "Search result is still present on the page",
            10);
  }

  // Получить найденные статьи
  public List<WebElement> getFoundArticles() {
    this.waitForElementsPresent(
            SEARCH_RESULT,
            "Cannot find articles",
            2);
    //return driver.findElements(By.id(SEARCH_RESULT));
    return driver.findElements(this.getLocatorByString(SEARCH_RESULT));
  }

  // Отменяем поиск
  public void clickCancelSearch() {
    this.waitForElementAndClick(
            SEARCH_CANCEL_BTN,
            "Cannot find X to cancel search",
            2);
  }
}
