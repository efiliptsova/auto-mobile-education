package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;

public class CoreTestCase extends TestCase {

  protected AppiumDriver driver;

  protected void setUp() throws Exception {
    super.setUp();
    // включаем драйвер
    driver = Platform.getInstance().getDriver();
    // Задание 7*
    driver.rotate(ScreenOrientation.PORTRAIT);
    skipWelcomePageForIOSApp();
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

  // функция скипа welcome-экрана для iOS
  private void skipWelcomePageForIOSApp()
  {
    if (Platform.getInstance().isIOS())
    {
      WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
      welcomePageObject.clickSkip();
    }
  }

}
