package org.lisak.pguide.dao;

import org.lisak.pguide.model.Article;
import org.lisak.pguide.model.Content;
import org.lisak.pguide.model.Image;
import org.lisak.pguide.model.Profile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 06.08.13
 * Time: 17:22
 */
public interface ContentDao {
    public void save(Content content);
    public Content get(String id);
    public void delete(String id);
    public List<Article> getArticles();
    public List<Image> getImages();
    public List<Image> getImages(String filter);
    public List<Profile> getProfiles(String categoryId);
    public List<Profile> getProfiles();
}
