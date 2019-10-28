package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

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
    action
            .press(right_x, middle_y)
            .waitAction(300)
            .moveTo(left_x, middle_y)
            .release()
            .perform();
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
}
