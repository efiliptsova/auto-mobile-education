package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Platform {

  private  static final String
          PLATFORM_ANDROID = "android",
          PLATFORM_IOS = "ios",
          APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

  public Boolean isAndroid()
  {
    return isPlatform(PLATFORM_ANDROID);
  }

  public Boolean isIOS()
  {
    return isPlatform(PLATFORM_IOS);
  }

  public AppiumDriver getDriver() throws Exception {
    URL url = new URL(APPIUM_URL);
    if (isAndroid())
    {
      return new AndroidDriver(url, getAndroidCapabilities());
    }
    else if (isIOS())
    {
      return new IOSDriver(url, getIOSCapabilities());
    }
    else
    {
      throw new Exception("Cannot detect type of the driver. Platform value: " + getPlatform());
    }
  }

  private DesiredCapabilities getAndroidCapabilities()
  {
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability("platformName", "Android");
    cap.setCapability("deviceName", "AndroidTestDevice");
    cap.setCapability("platformVersion", "6.0");
    cap.setCapability("automationName", "Appium");
    cap.setCapability("appPackage", "org.wikipedia");
    cap.setCapability("appActivity", ".main.MainActivity");
    //cap.setCapability("app", "F:/Projects/GitHub/mobile-auto-education/javaAppiumAutomation/apks/org.wikipedia.apk");
    cap.setCapability("app", "D:/PROJECTS/Руководство/GitHub/mobile-auto-education/javaAppiumAutomation/apks/org.wikipedia.apk");
    return cap;
  }

  private DesiredCapabilities getIOSCapabilities()
  {
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability("platformName", "iOS");
    cap.setCapability("deviceName", "iPhone SE");
    cap.setCapability("platformVersion", "11.2");
    cap.setCapability("app", "/Users/relex/Desktop/elena/GitHub/mobile-auto-education/javaAppiumAutomation/apks/Wikipedia.app");
    return cap;
  }

  private String getPlatform()
  {
    return System.getenv("PLATFORM");
  }

  private Boolean isPlatform(String myPlatform)
  {
    String platform = this.getPlatform();
    return platform.equals(myPlatform);
  }


}
