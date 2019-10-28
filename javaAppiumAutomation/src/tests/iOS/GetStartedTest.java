package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
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