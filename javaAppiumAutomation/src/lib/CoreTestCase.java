package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CoreTestCase extends TestCase {

  private  static final String
          PLATFORM_ANDROID = "android",
          PLATFORM_IOS = "ios";

  protected AppiumDriver driver;
  private String appiumURL = "http://127.0.0.1:4723/wd/hub";

  protected void setUp() throws Exception {
    super.setUp();
    DesiredCapabilities cap = getCapabilitiesByPlatformEnv();
    // включаем драйвер
    driver = getDriverByPlatformEnv(new URL(appiumURL), cap);

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

  private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
    String platform = System.getenv("PLATFORM");
    DesiredCapabilities cap = new DesiredCapabilities();
    if (platform.equals(PLATFORM_ANDROID))
    {
      cap.setCapability("platformName", "Android");
      cap.setCapability("deviceName", "AndroidTestDevice");
      cap.setCapability("platformVersion", "6.0");
      cap.setCapability("automationName", "Appium");
      cap.setCapability("appPackage", "org.wikipedia");
      cap.setCapability("appActivity", ".main.MainActivity");
      //cap.setCapability("app", "F:/Projects/GitHub/mobile-auto-education/javaAppiumAutomation/apks/org.wikipedia.apk");
      cap.setCapability("app", "D:/PROJECTS/Руководство/GitHub/mobile-auto-education/javaAppiumAutomation/apks/org.wikipedia.apk");

    }
    else if (platform.equals(PLATFORM_IOS))
    {
      cap.setCapability("platformName", "iOS");
      cap.setCapability("deviceName", "iPhone SE");
      cap.setCapability("platformVersion", "11.2");
      cap.setCapability("app", "/Users/relex/Desktop/elena/GitHub/mobile-auto-education/javaAppiumAutomation/apks/Wikipedia.app");
    }
    else
    {
      throw new Exception("Cannot get run platform from env variable. Platform valiue = " + platform);
    }
    return cap;
  }

  private AppiumDriver getDriverByPlatformEnv(URL url, DesiredCapabilities cap) throws Exception {
    String platform = System.getenv("PLATFORM");
    AppiumDriver driver;
    if (platform.equals(PLATFORM_ANDROID))
    {
      driver = new AndroidDriver(url, cap);
    }
    else if (platform.equals(PLATFORM_IOS))
    {
      driver = new IOSDriver(url, cap);
    }
    else
    {
      throw new Exception("Cannot get run platform from env variable. Platform valiue = " + platform);
    }
     return driver;
  }

}
