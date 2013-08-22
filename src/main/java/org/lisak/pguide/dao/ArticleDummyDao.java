package org.lisak.pguide.dao;

import org.lisak.pguide.model.Article;
import org.lisak.pguide.model.Content;
import org.lisak.pguide.model.Image;
import org.lisak.pguide.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 09.08.13
 * Time: 14:23
 */
public class ArticleDummyDao implements ContentDao {
    Article article;

    public ArticleDummyDao() {
        article = new Article();
        article.setId("testArticleId");
        article.setTitle("Test Article");
        article.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor " +
                "in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " +
                "sunt in culpa qui officia deserunt mollit anim id est laborum.");
    }

    @Override
    public void save(Content content) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Article get(String id) {
        return article;
    }

    @Override
    public void delete(String id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public List<Image> getImages() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Image> getImages(String filter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Profile> getProfiles(String categoryId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Profile> getProfiles() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Article> getArticles() {
        List<Article> l = new ArrayList<Article>();
        l.add(article);
        return l;
    }
}
