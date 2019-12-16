package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

public abstract class MyListPageObject extends MainPageObject{

  protected static String
          LIST_NAME_TPL,
          ARTICLE_NAME_IN_LIST_TPL,
          READING_LIST_TAB;

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
    if (Platform.getInstance().isIOS())
    {

      waitForElementAndClick(
              READING_LIST_TAB,
              "Cannot find tab 'Reading list'",
              10);
    }
    String loc = getListNameLocator(listName);
    waitForElementAndClick(
            loc,
            "Cannot find created folder",
            10);
  }

  //удаление статьи з списка
  public void deleteArticleFromList(String articleName)
  {
    String article_xpath = getArticleNameInListLocator(articleName.toLowerCase());
    swipeElementToLeft(
            article_xpath,
            "Cannot find saved article"
    );
    if (Platform.getInstance().isIOS())
    {
      clickElementToTheRightUpperCorner(getArticleNameInListLocator(articleName), "Cannot find saved article");
    }
  }

  //убеждаемся, что статья отсутствует в списке
  public void checkThatArticleNotPresentInList(String articleName) {
    waitForElementNotPresent(
            getArticleNameInListLocator(articleName.toLowerCase()),
            "Deleted article is still exist",
            5);
  }

  //убеждаемся, что статья присутствует в списке
  public void checkThatArticlePresentInList(String articleName) {
    waitForElementPresent(
            getArticleNameInListLocator(articleName.toLowerCase()),
            "Cannot find saved article",
            5);
  }

  //выбор статьи в списке
  public void selectArticleInList(String articleName) {
    waitForElementAndClick(
            getArticleNameInListLocator(articleName.toLowerCase()),
            "Cannot find navigation button to My List",
            10);
  }

}
