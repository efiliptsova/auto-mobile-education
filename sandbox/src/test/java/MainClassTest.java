import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

  @Test
  public void testGetLocalNumber() {
    MainClass mc = new MainClass();
    Assert.assertTrue("getLocalNumber() is not equal 14", mc.getLocalNumber() == 14);
  }
}
