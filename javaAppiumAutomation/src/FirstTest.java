import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
    cap.setCapability("app", "F:/Projects/GitHub/mobile-auto-education/javaAppiumAutomation/apks/org.wikipedia.apk");

    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void firstTest() {
    System.out.println("First test run!");
  }
}
