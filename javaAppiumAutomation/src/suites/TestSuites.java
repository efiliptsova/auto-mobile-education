package suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.ChangeAppConditionTests;
import tests.SearchTests;

@RunWith(Suite.class)

@Suite.SuiteClasses(
        {
                ArticleTests.class,
                ChangeAppConditionTests.class,
                SearchTests.class
        }
)
public class TestSuites {
}
