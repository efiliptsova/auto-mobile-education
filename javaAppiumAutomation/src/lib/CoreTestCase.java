package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {

  protected AppiumDriver driver;
  private String appiumURL = "http://127.0.0.1:4723/wd/hub";

  protected void setUp() throws Exception {
    super.setUp();

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
    driver = new AndroidDriver(new URL(appiumURL), cap);

    // Задание 7*
    driver.rotate(ScreenOrientation.PORTRAIT);
  }

  public void tearDown() throws Exception {
    driver.quit();
    super.tearDown();
  }

  protected void rotateScreenPortrait()
  {
    driver.rotate(ScreenOrientation.PORTRAIT);
  }

  protected void rotateScreenLandscape()
  {
    driver.rotate(ScreenOrientation.LANDSCAPE);
  }

  protected ScreenOrientation getScreenOrientation()
  {
    return driver.getOrientation();
  }

}
