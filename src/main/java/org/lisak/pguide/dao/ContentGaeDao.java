package org.lisak.pguide.dao;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import org.lisak.pguide.exceptions.NoSuchEntity;
import org.lisak.pguide.model.Article;
import org.lisak.pguide.model.Content;
import org.lisak.pguide.model.Image;
import org.lisak.pguide.model.Profile;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 06.08.13
 * Time: 17:21
 */
@Entity
public class ContentGaeDao implements ContentDao {
    private static ContentGaeDao staticDao;

    public static ContentGaeDao getStaticDao() {
        return staticDao;
    }

    public ContentGaeDao() {
        ObjectifyService.register(Content.class);
        ObjectifyService.register(Article.class);
        ObjectifyService.register(Image.class);
        ObjectifyService.register(Profile.class);

        staticDao = this;
    }

    //loads article from datastore
    @Override
    public Content get(String contentId) {
        Content _a = ofy().load().key(com.googlecode.objectify.Key.create(Content.class, contentId)).now();
        if(_a == null) {
            throw new NoSuchEntity();
        } else {
            return _a;
        }
    }

    @Override
    public void delete(String id) {
        Content _a = ofy().load().key(com.googlecode.objectify.Key.create(Content.class, id)).now();
        ofy().delete().entity(_a).now();
    }

    @Override
    public void save(Content content) {
        ofy().save().entity(content).now();
    }

    public List<Article> getArticles() {
        return ofy().load().type(Article.class).list();
    }

    @Override
    public List<Image> getImages() {
        return ofy().load().type(Image.class).list();
    }

    @Override
    public List<Image> getImages(String filter) {
        List<Image> _l =  ofy().load().type(Image.class).list();
        List<Image> result = new ArrayList<Image>();
        for(Image i : _l) {
            if(i.getId().contains(filter)) {
                result.add(i);
            }
        }
        return result;
    }

    @Override
    public List<Profile> getProfiles(String categoryId) {
        List<Profile> p = ofy().load().type(Profile.class).filter("category", categoryId).list();
        List<Content> p1 = ofy().load().type(Content.class).list();
        return p;
    }

    @Override
    public List<Profile> getProfiles() {
        return ofy().load().type(Profile.class).list();
    }
    /*
    public <T extends Content> List<T> getAll() {
        return ofy().load().type(T).list();
    }
    */

}
