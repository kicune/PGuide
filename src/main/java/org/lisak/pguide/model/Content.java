package org.lisak.pguide.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created with IntelliJ IDEA.

 * User: lisak
 * Date: 06.08.13
 * Time: 17:19
 */

@Entity
public class Content {
    @Id protected String id;

    public Content() {}

    public Content(String id)  {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "\"" + this.getId() + "\"";
    }
}
