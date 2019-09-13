import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
    waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
            "Cannot find 'Search Wikipedia' input",
            2);
    waitForElementsPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            10);
    waitForElementAndClick(
            By.xpath("//android.widget.ImageView[@content-desc='More options']"),
            "Cannot find button to open article options",
            2);
    waitForElementAndClick(
            By.xpath("//*[@text='Add to reading list']"),
            "Cannot find option to add article to reading list",
            10);
    waitForElementAndClick(
            By.id("org.wikipedia:id/onboarding_button"),
            "Cannot find 'Got it' tip overlay",
            10);
    waitForElementAndClear(
            By.id("org.wikipedia:id/text_input"),
            "Cannot clear text into articles folder input",
            5);
    waitForElementAndSendKeys(
            By.id("org.wikipedia:id/text_input"),
            "AAAAA list",
            "Cannot put text into articles folder input",
            5);
    waitForElementAndClick(
            By.id("//*[@text='OK']"),
            "Cannot press 'OK' button",
            5);
    waitForElementAndClick(
            By.id("//android.widget.ImageView[@content-desc='Navigate up']"),
            "Cannot close article, cannot find X link",
            2);


    //Сохраняет две статьи в одну папку

    //Удаляет одну из статей

    //Убеждается, что вторая осталась

    //Переходит в неё и убеждается, что title совпадает
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

}
