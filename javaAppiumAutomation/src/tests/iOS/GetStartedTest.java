package tests.iOS;

import lib.CoreTestCase;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForWelcomeScreen1();
    }
}