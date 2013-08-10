package org.lisak.pguide.dao;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.cmd.Query;
import org.lisak.pguide.exceptions.NoSuchEntity;
import org.lisak.pguide.model.Article;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 06.08.13
 * Time: 17:21
 */
@Entity
public class ArticleGaeDao implements ArticleDao {

    public ArticleGaeDao() {
        ObjectifyService.register(Article.class);
    }

    //loads article from datastore
    @Override
    public Article get(String articleId) {
        Article _a = ofy().load().key(com.googlecode.objectify.Key.create(Article.class, articleId)).now();
        if(_a == null) {
            throw new NoSuchEntity();
        } else {
            return _a;
        }
    }

    @Override
    public void save(Article article) {
        ofy().save().entity(article).now();
    }

    public List<Article> getAll() {
        return ofy().load().type(Article.class).list();
    }

}
