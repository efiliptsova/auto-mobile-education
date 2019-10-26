package lib.ui;

public class WelcomePageObject extends MainPageObject{

  public WelcomePageObject(AppiumDriver driver) {
    super(driver);
  }

  public void waitForWelcomeScreen1() {
    this.waitForElementAndClick(By.id("Learn more about Wikipedia", "Connot find 'Learn more about Wikipedia'", 10));
  }
}
