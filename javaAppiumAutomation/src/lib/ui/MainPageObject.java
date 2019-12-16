package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
//import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

import lib.Platform;

public class MainPageObject {

  protected AppiumDriver driver;

  public MainPageObject(AppiumDriver driver) {
    this.driver = driver;
  }

  public WebElement waitForElementAndClick(String locator, String error_message, long timeout) {
    WebElement el = waitForElementPresent(locator, error_message, timeout);
    el.click();
    return el;
  }

  public WebElement simpleClick(String locator, String error_message, long timeout) {
    By by = this.getLocatorByString(locator);
    WebElement el = driver.findElement(by);
    el.click();
    return el;
  }

  public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeout) {
    WebElement el = waitForElementPresent(locator, error_message, timeout);
    el.sendKeys(value);
    return el;
  }

  public WebElement waitForElementAndClear(String locator, String error_message, long timeout) {
    WebElement el = waitForElementPresent(locator, error_message, timeout);
    el.clear();
    return el;
  }

  public WebElement waitForElementPresent(String locator, String error_message, long timeout) {
    By by = this.getLocatorByString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    return  wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public boolean waitForElementsPresent(String locator, String error_message, long timeout) {
    By by = this.getLocatorByString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    wait.until(ExpectedConditions.presenceOfElementLocated(by));
    int count = driver.findElements(by).size();
    if (count > 1)
      return true;
    else
      return false;
  }

  public boolean waitForElementNotPresent(String locator, String error_message, long timeout) {
    By by = this.getLocatorByString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(error_message + "\n");
    return  wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  public void swipeElementToLeft(String locator, String error_message) {
    WebElement el = waitForElementPresent(locator, error_message, 10);
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
    action.press(right_x, middle_y);
    action.waitAction(1000);
    if (Platform.getInstance().isAndroid()) {
      //action.moveTo(left_x, middle_y);
      action.moveTo(1, middle_y);
    }
    else {
      int offset_x = -1 * el.getSize().getWidth();
      action.moveTo(offset_x, 0);
    }
    action.release();
    action.perform();
  }

  public void assertElementPresent(String locator) {
    By by = this.getLocatorByString(locator);
    if (driver.findElements(by).size() == 0) {
      String message = "An element with locator " + by.toString() + " supposed to be present";
      throw new AssertionError(message);
    }
  }

  // id:locator //xpath:locator
  protected By getLocatorByString(String locatorWithType)
  {
    String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
    String byType = explodedLocator[0];
    String locator = explodedLocator[1];

    if (byType.equals("xpath"))
    {
      return By.xpath(locator);
    }
    else if (byType.equals("id"))
    {
      return By.id(locator);
    }
    else
    {
      throw new IllegalArgumentException("Cannot get type of locator " + locatorWithType);
    }
  }

  public void clickElementToTheRigthUpperCorner(String locator, String error_message)
  {
    // переходим к родительскому элементу
    WebElement el = waitForElementPresent(locator + "/..", error_message, 10);
    // получить самую левую точку элемента по оси х
    int right_x = el.getLocation().getX();
    // получить самую верхнюю точку элемента по оси у
    int upper_y = el.getLocation().getY();
    int lower_y = upper_y + el.getSize().getHeight();
    int middle_y = (upper_y + lower_y)/2;
    int width = el.getSize().getWidth();

    // хотим кликнуть в правый верхний угол для элемента
    // находим правый верхний угол отступаем от него на некоторое значение (3)
    // эта точка будет на 3 пикселя левее, чем полная ширина элемента
    int point_to_click_x = (right_x + width) - 3;
    // точка будет находится ровно по середине по высоте элемента
    int point_to_click_y = middle_y;
    TouchAction action = new TouchAction(driver);
    action.tap(point_to_click_x, point_to_click_y);
  }
}
