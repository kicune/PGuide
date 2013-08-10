package org.lisak.pguide.dao;

import org.lisak.pguide.model.Article;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 06.08.13
 * Time: 17:22
 */
public interface ArticleDao {
    public void save(Article article);
    public Article get(String id);
    public List<Article> getAll();
}
