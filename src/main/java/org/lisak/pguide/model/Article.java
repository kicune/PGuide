package org.lisak.pguide.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import org.lisak.pguide.exceptions.NoSuchEntity;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 06.08.13
 * Time: 17:12
 */
@Entity
public class Article {
    @Id private String id;
    String title;
    String text;

    public Article() {
    }

    public Article(String id, String title, String text) {
        this.title = title;
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void save() {

    }

}
