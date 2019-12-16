package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

  // Задание 5
  @Test
  public void testSaveTwoArticleToList()
  {
    //
    // 1. Сохраняет две статьи в одну папку
    //
    String searchText = "java";
    String listName = "ABC3 list";
    //String articleName = "Programming language";
    String articleName = "rogramming language";
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.saveArticle(searchText, true, articleName, listName);

    String secondArticleName = "Object-oriented programming language";
    articlePageObject.saveArticle(searchText, false, secondArticleName, listName);
    //
    // 2. Удаляет одну из статей
    //
    NavigationUI navigationUI = NavigationUIFactory.get(driver);
    navigationUI.goToMyList();
    MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
    myListPageObject.selectListByName(listName);
    //удаление статьи
    myListPageObject.deleteArticleFromList(articleName);
    //убеждаемся, что  удаленная статья отсутствует
    myListPageObject.checkThatArticleNotPresentInList(articleName);
    //
    // 3. Убеждается, что вторая осталась
    //
    myListPageObject.checkThatArticlePresentInList(secondArticleName);
    //
    // 4. Переходит в неё и убеждается, что title совпадает
    //
    myListPageObject.selectArticleInList(secondArticleName);
    assertEquals(articlePageObject.getArticleTitle(), "Java (programming language)");
  }

  // Задание 6
  @Test
  public void testAssertArticleTitle() {
    String searchText = "java";
    String articleName = "Programming language";

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchText);
    searchPageObject.waitForSearchResultAndSelectArticle(articleName);
    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.checkArticleTitleLoaded();
  }

  }
