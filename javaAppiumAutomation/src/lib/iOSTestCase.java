package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestCase extends TestCase {
  protected AppiumDriver driver;
  private String appiumURL = "http://127.0.0.1:4723/wd/hub";

  protected void setUp() throws Exception {
    super.setUp();

    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability("platformName", "iOS");
    cap.setCapability("deviceName", "iPhone SE");
    cap.setCapability("platformVersion", "11.2");
    cap.setCapability("app", "/Users/relex/Desktop/elena/GitHub/mobile-auto-education/javaAppiumAutomation/apks/Wikipedia.app");

    // включаем андройд драйвер
    driver = new IOSDriver(new URL(appiumURL), cap);

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
