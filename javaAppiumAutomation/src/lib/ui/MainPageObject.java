package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageObject {

  protected AppiumDriver driver;

  public MainPageObject(AppiumDriver driver) {
    this.driver = driver;
  }

  //Сохраняет статью articleName в папку folderName
  public void saveArticle(String searchText, boolean newFolder, String articleName, String folderName) {
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

  public WebElement waitForElementAndClick(By by, String error_message, long timeout) {
    WebElement el = waitForElementPresent(by, error_message, timeout);
    el.click();
    return el;
  }

  public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeout) {
    WebElement el = waitForElementPresent(by, error_message, timeout);
    el.sendKeys(value);
    return el;
  }

  public WebElement waitForElementAndClear(By by, String error_message, long timeout) {
    WebElement el = waitForElementPresent(by, error_message, timeout);
    el.clear();
    return el;
  }

  public WebElement waitForElementPresent(By by, String error_message, long timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    return  wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public boolean waitForElementsPresent(By by, String error_message, long timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    wait.until(ExpectedConditions.presenceOfElementLocated(by));
    int count = driver.findElements(by).size();
    if (count > 1)
      return true;
    else
      return false;
  }

  public boolean waitForElementNotPresent(By by, String error_message, long timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    return  wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  public void swipeElementToLeft(By by, String error_message) {
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
