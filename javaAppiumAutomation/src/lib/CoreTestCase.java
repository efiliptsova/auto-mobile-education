package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

public class CoreTestCase extends TestCase {

  protected AppiumDriver driver;
  protected Platform platform;

  protected void setUp() throws Exception {
    super.setUp();
    // включаем драйвер
    this.platform = new Platform();
    platform.getDriver();

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
