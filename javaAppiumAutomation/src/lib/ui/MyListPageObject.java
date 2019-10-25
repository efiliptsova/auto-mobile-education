package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject{

  private static final String
          LIST_NAME_TPL = "//android.widget.TextView[@text='{LIST_NAME}']",
          ARTICLE_NAME_IN_LIST_TPL = "//*[@text='{ARTICLE_NAME}']";

  public MyListPageObject(AppiumDriver driver) {
    super(driver);
  }

  /*TEMPLATE_METHODs*/
  private static String getListNameLocator(String listName) {
    return LIST_NAME_TPL.replace("{LIST_NAME}", listName);
  }

  private static String getArticleNameInListLocator(String articleName) {
    return ARTICLE_NAME_IN_LIST_TPL.replace("{ARTICLE_NAME}", articleName);
  }
  /*TEMPLATE_METHODs*/

  public void selectListByName(String listName) {
    waitForElementAndClick(
            By.xpath(getListNameLocator(listName)),
            "Cannot find created folder",
            10);
  }

  //удаление статьи з списка
  public void deleteArticleFromList(String articleName)
  {
    swipeElementToLeft(
            By.xpath(getArticleNameInListLocator(articleName.toLowerCase())),
            "Cannot find saved article"
    );
  }

  //убеждаемся, что статья отсутствует в списке
  public void checkThatArticleNotPresentInList(String articleName) {
    waitForElementNotPresent(
            By.xpath(getArticleNameInListLocator(articleName.toLowerCase())),
            "Deleted article is still exist",
            5);
  }

  //убеждаемся, что статья присутствует в списке
  public void checkThatArticlePresentInList(String articleName) {
    waitForElementPresent(
            By.xpath(getArticleNameInListLocator(articleName.toLowerCase())),
            "Cannot find saved article",
            5);
  }

  //выбор статьи в списке
  public void selectArticleInList(String articleName) {
    waitForElementAndClick(
            By.xpath(getArticleNameInListLocator(articleName.toLowerCase())),
            "Cannot find navigation button to My List",
            10);
  }

}
