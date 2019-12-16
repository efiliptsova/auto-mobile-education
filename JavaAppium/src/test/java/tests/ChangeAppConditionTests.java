package tests;

import lib.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

public class ChangeAppConditionTests extends CoreTestCase {

  // Задание 7* - решение см. в блоке setUp(), ниже - просто тест для отладки
  @Test
  public void testRotation() {
    assertTrue(getScreenOrientation().equals(ScreenOrientation.PORTRAIT));
    rotateScreenLandscape();
    rotateScreenPortrait();
    rotateScreenLandscape();
  }
}
