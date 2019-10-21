import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.List;


public class FirstTest extends CoreTestCase {
  private MainPageObject mainPageObject;

  protected void setUp() throws Exception {
    super.setUp();
    mainPageObject = new MainPageObject(driver);
  }

  @Test
  public void testFirst() {
    WebElement el = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
    el.click();
    System.out.println("First test run!");
  }

  // Задание 2
  @Test
  public void testSearchTextExist() {
    /*mainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            5);
    WebElement searchField = mainPageObject.waitForElementPresent(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot find search input",
            5);
    Assert.assertEquals(searchField.getText(), "Search…");*/

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    //Assert.assertEquals(searchField.getText(), "Search…");
  }

  @Test
  public void testSearch()
  {
    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForSerchResult("Object-oriented programming language");
  }

  // Задание 3
  @Test
  public void testSearchCancel() throws InterruptedException {
    /*
    // Ищем какое-то слово
    mainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            2);
    mainPageObject.waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            "Java",
            "Cannot find search input",
            2);
    // Убеждаемся, что найдено несколько статей
    mainPageObject.waitForElementsPresent(
            By.id("org.wikipedia:id/page_list_item_container"),
            "Cannot find several articles",
            2); */
    // "//*[@resource_id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']";
    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.waitForSerchResult("Object-oriented programming language");

    // Отменяем поиск
    mainPageObject.waitForElementAndClick(
            By.id("org.wikipedia:id/search_close_btn"),
            "Cannot find X to cancel search",
            2);
    // Убеждаемся, что результат поиска пропал
    mainPageObject.waitForElementNotPresent(
            By.id("org.wikipedia:id/page_list_item_container"),
            "Search result is still present on the page",
            10);
  }

  // Задание 4
  @Test
  public void testCheckWords() throws InterruptedException {
    // Ищем какое-то слово
    String searchText = "java";
    mainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            2);
    mainPageObject.waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            searchText,
            "Cannot find search input",
            2);
    // Убеждаемся, что найдено несколько статей
    mainPageObject.waitForElementsPresent(
            By.id("org.wikipedia:id/page_list_item_container"),
            "Cannot find several articles",
            5);

    List<WebElement> list = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"));
    String titleText,descText = "";
    for (WebElement el : list)
    {
      titleText = el.findElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']")).getText();
      descText = el.findElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']")).getText();;
      Assert.assertTrue(String.format("Found element without text '%s'", searchText),
              (titleText.toLowerCase().contains(searchText) || descText.toLowerCase().contains(searchText)));
    }
  }

  // Задание 5
  @Test
  public void testSaveTwoArticleToList()
  {
    //
    // 1. Сохраняет две статьи в одну папку
    //
    String searchText = "java";
    String listName = "ABC3 list";

    String articleName = "Programming language";
    mainPageObject.saveArticle(searchText, true, articleName, listName);

    String secondArticleName = "Object-oriented programming language";
    mainPageObject.saveArticle(searchText, false, secondArticleName, listName);

    //
    // 2. Удаляет одну из статей
    //

    //проверяем, что статья действительно добавилась
    mainPageObject.waitForElementAndClick(
            By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
            "Cannot find navigation button to My List",
            15);

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    mainPageObject.waitForElementAndClick(
            By.xpath("//android.widget.TextView[@text='" + listName + "']"),
            "Cannot find created folder",
            10);
    //удаление статьи
    mainPageObject.swipeElementToLeft(
            By.xpath("//*[@text='" + articleName.toLowerCase() + "']"),
            "Cannot find saved article"
    );
    //убеждаемся, что  удаленная статья отсутствует
    mainPageObject.waitForElementNotPresent(
            By.xpath("//*[@text='" + articleName.toLowerCase() + "']"),
            "Deleted article is still exist",
            5);

    //
    // 3. Убеждается, что вторая осталась
    //
    mainPageObject.waitForElementPresent(
            By.xpath("//*[@text='" + secondArticleName.toLowerCase() + "']"),
            "Cannot find saved article",
            5);

    //
    // 4. Переходит в неё и убеждается, что title совпадает
    //
    mainPageObject.waitForElementAndClick(
            By.xpath("//*[@text='" + secondArticleName.toLowerCase() + "']"),
            "Cannot find navigation button to My List",
            10);
    WebElement el = mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Cannot find article title", 10);
    Assert.assertEquals(el.getText(), "Java (programming language)");
  }

  // Задание 6
  @Test
  public void testAssertArticleTitle() {
    String searchText = "java";
    String articleName = "Programming language";

    mainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            2);
    mainPageObject.waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            searchText,
            "Cannot find search input",
            2);
    mainPageObject.waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleName + "']"),
            "Cannot find 'Search Wikipedia' input",
            2);
    assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
  }

  // Задание 7* - решение см. в блоке @Before, ниже - просто тест для отладки
  @Test
  public void testRotation() {
    Assert.assertTrue(driver.getOrientation().equals(ScreenOrientation.PORTRAIT));
    driver.rotate(ScreenOrientation.LANDSCAPE);
    driver.rotate(ScreenOrientation.PORTRAIT);
    driver.rotate(ScreenOrientation.LANDSCAPE);
  }

  private void assertElementPresent(By by) {
    //WebElement el = driver.findElement(by);
    //if (el == null)
    if (driver.findElements(by).size() == 0)
    {
      String message = "An element with xpath locator " + by.toString() + " supposed to be present";
      throw new AssertionError(message);
    }
  }



}
