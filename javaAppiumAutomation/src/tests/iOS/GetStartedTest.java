package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        // пропускаем данный тест для платформы Android
        if (this.platform.isAndroid())
        {
            return;
        }
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForWelcomeScreen1();
        welcomePageObject.clickNext();
        welcomePageObject.waitForWelcomeScreen2();
        welcomePageObject.clickNext();
        welcomePageObject.waitForWelcomeScreen3();
        welcomePageObject.clickNext();
        welcomePageObject.waitForWelcomeScreen4();
        welcomePageObject.clickGetStarted();
    }
}