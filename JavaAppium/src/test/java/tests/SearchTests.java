package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

  // Задание 2
  @Test
  public void testSearchTextExist() {
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    assertEquals(searchPageObject.getSearchLineText(), "Search…");
  }

  // Задание 3
  @Test
  public void testSearchCancel() throws InterruptedException {
    // Ищем какое-то слово
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    // Убеждаемся, что найдено несколько статей
    searchPageObject.waitForSearchResult();
    // Отменяем поиск
    searchPageObject.clickCancelSearch();
    // Убеждаемся, что результат поиска пропал
    searchPageObject.waitForEmptySearchResult();
  }

  // Задание 4
  @Test
  public void testCheckWords() throws InterruptedException {
    // Ищем какое-то слово
    String searchText = "java";
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchText);
    // Убеждаемся, что найдено несколько статей
    searchPageObject.waitForSearchResult();
    // Получаем найденные статьи
    List<WebElement> list = searchPageObject.getFoundArticles();
    String titleText, descText = "";
    for (WebElement el : list)
    {
      ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
      titleText = articlePageObject.getArticleTitle(el);
      descText = articlePageObject.getArticleDescription(el);
      assertTrue(String.format("Found element without text '%s'", searchText),
              (titleText.toLowerCase().contains(searchText) ||
                      descText.toLowerCase().contains(searchText)));
    }
  }
}
