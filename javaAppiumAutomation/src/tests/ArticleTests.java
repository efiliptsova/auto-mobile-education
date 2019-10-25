package tests;

import lib.CoreTestCase;
import lib.ui.*;
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
    String articleName = "Programming language";
    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.saveArticle(searchText, true, articleName, listName);
    String secondArticleName = "Object-oriented programming language";
    articlePageObject.saveArticle(searchText, false, secondArticleName, listName);
    //
    // 2. Удаляет одну из статей
    //
    NavigationUI navigationUI = new NavigationUI(driver);
    navigationUI.goToMyList();
    MyListPageObject myListPageObject = new MyListPageObject(driver);
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

    SearchPageObject searchPageObject = new SearchPageObject(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(searchText);
    searchPageObject.waitForSearchResultAndSelectArticle(articleName);
    ArticlePageObject articlePageObject = new ArticlePageObject(driver);
    articlePageObject.checkArticleTitleLoaded();
  }

  }
