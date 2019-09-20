import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class FirstTest {

  private AppiumDriver driver;

  @Before
  public void setUp() throws MalformedURLException {
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability("platformName", "Android");
    cap.setCapability("deviceName", "AndroidTestDevice");
    cap.setCapability("platformVersion", "6.0");
    cap.setCapability("automationName", "Appium");
    cap.setCapability("appPackage", "org.wikipedia");
    cap.setCapability("appActivity", ".main.MainActivity");
    //cap.setCapability("app", "F:/Projects/GitHub/mobile-auto-education/javaAppiumAutomation/apks/org.wikipedia.apk");
    cap.setCapability("app", "D:/PROJECTS/Руководство/GitHub/mobile-auto-education/javaAppiumAutomation/apks/org.wikipedia.apk");

    // включаем андройд драйвер
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

    // Задание 7
    driver.rotate(ScreenOrientation.PORTRAIT);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void firstTest() {
    WebElement el = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
    el.click();
    System.out.println("First test run!");
  }

  // Задание 2
  @Test
  public void testSearchTextExist() {
    //driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]").click();
    //WebElement searchField = driver.findElementById("org.wikipedia:id/search_src_text");
    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            5);
    WebElement searchField = waitForElementPresent(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot find search input",
            5);
    Assert.assertEquals(searchField.getText(), "Search…");
  }

  // Задание 3
  @Test
  public void testSearchCancel() throws InterruptedException {
    // Ищем какое-то слово
    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            2);
    waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            "Java",
            "Cannot find search input",
            2);
    // Убеждаемся, что найдено несколько статей
    waitForElementsPresent(
            By.id("org.wikipedia:id/page_list_item_container"),
            "Cannot find several articles",
            2);
    // Отменяем поиск
    waitForElementAndClick(
            By.id("org.wikipedia:id/search_close_btn"),
            "Cannot find X to cancel search",
            2);
    // Убеждаемся, что результат поиска пропал
    waitForElementNotPresent(
            By.id("org.wikipedia:id/page_list_item_container"),
            "Search result is still present on the page",
            10);
  }

  // Задание 4 - НЕ ГОТОВО !!!
  @Test
  public void testCheckWords() throws InterruptedException {
    // Ищем какое-то слово
    String searchText = "java";
    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            2);
    waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            searchText,
            "Cannot find search input",
            2);
    // Убеждаемся, что найдено несколько статей
    waitForElementsPresent(
            By.id("org.wikipedia:id/page_list_item_container"),
            "Cannot find several articles",
            5);
    List<WebElement> list = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*"));
    String text1 = "";
    for (WebElement el : list)
    {
      text1 = el.getText();
      //Assert.assertTrue(String.format("Found element without text '%s'", searchText), text.contains("java"));
    }
  }

  // Задание 5
  @Test
  public void saveTwoArticleToList()
  {
    //
    // 1. Сохраняет две статьи в одну папку
    //
    String searchText = "java";
    String listName = "ABC3 list";

    String articleName = "Programming language";
    saveArticle(searchText, true, articleName, listName);

    String secondArticleName = "Object-oriented programming language";
    saveArticle(searchText, false, secondArticleName, listName);

    //
    // 2. Удаляет одну из статей
    //

    //проверяем, что статья действительно добавилась
    waitForElementAndClick(
            By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
            "Cannot find navigation button to My List",
            15);

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    waitForElementAndClick(
            By.xpath("//android.widget.TextView[@text='" + listName + "']"),
            "Cannot find created folder",
            10);
    //удаление статьи
    swipeElementToLeft(
            By.xpath("//*[@text='" + articleName.toLowerCase() + "']"),
            "Cannot find saved article"
    );
    //убеждаемся, что  удаленная статья отсутствует
    waitForElementNotPresent(
            By.xpath("//*[@text='" + articleName.toLowerCase() + "']"),
            "Deleted article is still exist",
            5);

    //
    // 3. Убеждается, что вторая осталась
    //
    waitForElementPresent(
            By.xpath("//*[@text='" + secondArticleName.toLowerCase() + "']"),
            "Cannot find saved article",
            5);

    //
    // 4. Переходит в неё и убеждается, что title совпадает
    //
    waitForElementAndClick(
            By.xpath("//*[@text='" + secondArticleName.toLowerCase() + "']"),
            "Cannot find navigation button to My List",
            10);
    WebElement el = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Cannot find article title", 10);
    Assert.assertEquals(el.getText(), "Java (programming language)");
  }

  // Задание 6
  @Test
  public void assertArticleTitle() {
    String searchText = "java";
    String articleName = "Programming language";

    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            2);
    waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            searchText,
            "Cannot find search input",
            2);
    waitForElementAndClick(
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

  //Сохраняет статью articleName в папку folderName
  private void saveArticle(String searchText, boolean newFolder, String articleName, String folderName) {
    waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            2);
    waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            searchText,
            "Cannot find search input",
            2);
    waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleName + "']"),
            "Cannot find 'Search Wikipedia' input",
            2);
    waitForElementsPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15);
    waitForElementAndClick(
            By.xpath("//android.widget.ImageView[@content-desc='More options']"),
            "Cannot find button to open article options",
            5);
    waitForElementAndClick(
            By.xpath("//*[@text='Add to reading list']"),
            "Cannot find option to add article to reading list",
            5);
    // если содаем новую папку
    if (newFolder == true) {
      waitForElementAndClick(
              By.id("org.wikipedia:id/onboarding_button"),
              "Cannot find 'Got it' tip overlay",
              5);
      waitForElementAndClear(
              By.id("org.wikipedia:id/text_input"),
              "Cannot clear text into articles folder input",
              5);
      waitForElementAndSendKeys(
              By.id("org.wikipedia:id/text_input"),
              folderName,
              "Cannot put text into articles folder input",
              5);
      waitForElementAndClick(
              By.xpath("//*[@text='OK']"),
              "Cannot press 'OK' button",
              2);
    } // если добавляем статью в уже существующую папку
    else
    {
      waitForElementAndClick(
              By.xpath("//android.widget.TextView[@text='" + folderName + "']"),
              "Cannot find 'Got it' tip overlay",
              5);
    }
    // закрытие статьи
    waitForElementAndClick(
            By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
            "Cannot close article, cannot find X link",
            2);
  }

  private WebElement waitForElementAndClick(By by, String error_message, long timeout) {
    WebElement el = waitForElementPresent(by, error_message, timeout);
     el.click();
    return el;
  }

  private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeout) {
    WebElement el = waitForElementPresent(by, error_message, timeout);
    el.sendKeys(value);
    return el;
  }

  private WebElement waitForElementAndClear(By by, String error_message, long timeout) {
    WebElement el = waitForElementPresent(by, error_message, timeout);
    el.clear();
    return el;
  }

  private WebElement waitForElementPresent(By by, String error_message, long timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    return  wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  private boolean waitForElementsPresent(By by, String error_message, long timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    wait.until(ExpectedConditions.presenceOfElementLocated(by));
    int count = driver.findElements(by).size();
    if (count > 1)
      return true;
    else
      return false;
  }

  private boolean waitForElementNotPresent(By by, String error_message, long timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    return  wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  protected void swipeElementToLeft(By by, String error_message) {
    WebElement el = waitForElementPresent(by, error_message, 10);
    // получить самую левую точку элемента по оси х
    int left_x = el.getLocation().getX();
    // получить самую правую точку элемента по оси х
    int right_x = left_x + el.getSize().getWidth();
    // получить самую верхнюю точку элемента по оси у
    int upper_y = el.getLocation().getY();
    int lower_y = upper_y + el.getSize().getHeight();
    int middle_y = (upper_y + lower_y)/2;

    // инициализируем драйвер
    TouchAction action = new TouchAction(driver);
    action
            .press(right_x, middle_y)
            .waitAction(300)
            .moveTo(left_x, middle_y)
            .release()
            .perform();
  }

}
