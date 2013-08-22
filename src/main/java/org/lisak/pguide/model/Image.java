package org.lisak.pguide.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 10.08.13
 * Time: 23:24
 */

@EntitySubclass(index = true)
public class Image extends Content {
    private byte[] data;
    @Index private String caption;
    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Image() {
        super();
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
            this.data = data;
    }

}
