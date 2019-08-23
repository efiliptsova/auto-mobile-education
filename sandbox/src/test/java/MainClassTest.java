import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
  private MainClass mc = new MainClass();

  @Test
  public void testGetLocalNumber() {
    Assert.assertTrue("getLocalNumber() is not equal 14", mc.getLocalNumber() == 14);
  }

  @Test
  public void testGetClassNumber() {
    Assert.assertTrue("getClassNumber() is more than 45", mc.getClassNumber() > 45);
  }
}
