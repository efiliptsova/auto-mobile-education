import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


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
    driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]").click();
    WebElement searchField = driver.findElementById("org.wikipedia:id/search_src_text");
    String text = searchField.getText();
    Assert.assertEquals(text, "Search…");
  }

  // Задание 3




}
