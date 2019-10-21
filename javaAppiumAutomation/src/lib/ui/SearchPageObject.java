package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{

  private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_INPUT = "org.wikipedia:id/search_src_text",
    //SEARCH_RESULT = "org.wikipedia:id/page_list_item_container",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource_id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";

  public SearchPageObject(AppiumDriver driver) {
    super(driver);
  }

  public void initSearchInput()
  {
    this.waitForElementAndClick(
            By.xpath(SEARCH_INIT_ELEMENT),
            "Cannot find 'Search Wikipedia' input",
            2);
    // ?
    this.waitForElementPresent(
            By.xpath(SEARCH_INIT_ELEMENT),
            "Cannot find search input after clicking search init element",
            2);
  }

  public void typeSearchLine(String searchText)
  {
    this.waitForElementAndSendKeys(
            By.id(SEARCH_INPUT),
            searchText,
            "Cannot find and type into search input",
            2);
  }

  /*TEMPLATE_METHODs*/
  private static String getResultSearchElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
  }
  /*TEMPLATE_METHODs*/

  public void waitForSerchResult(String substring) {
    String searchResultXpath = getResultSearchElement(substring);
    // Убеждаемся, что найдено несколько статей
    this.waitForElementsPresent(
            By.id(searchResultXpath),
            "Cannot find search result with substring " + substring,
            2);
  }
}
