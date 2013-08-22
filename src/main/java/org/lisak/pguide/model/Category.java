package org.lisak.pguide.model;

import com.googlecode.objectify.annotation.Embed;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 13.08.13
 * Time: 14:49
 */
@Embed
public class Category {
    private String id;
    private String name;
    private String iconUrl;

    public Category() {}

    public Category(String iconUrl, String id, String name) {
        this.iconUrl = iconUrl;
        this.id = id;
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
