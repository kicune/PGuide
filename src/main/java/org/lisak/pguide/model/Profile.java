package org.lisak.pguide.model;

import com.googlecode.objectify.annotation.EntitySubclass;
import geo.gps.Coordinates;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 06.08.13
 * Time: 17:12
 */

@EntitySubclass(index = true)
public class Profile extends Content {
    private String name;
    private String url;
    private Coordinates gpsCoords;
    private OpeningHours openingHours;
    private String address;
    private String perex;
    private String text;
    private ProfileAttributes profileAttributes;
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategory(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coordinates getGpsCoords() {
        return gpsCoords;
    }

    public void setGpsCoords(Coordinates gpsCoords) {
        this.gpsCoords = gpsCoords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public String getPerex() {
        return perex;
    }

    public void setPerex(String perex) {
        this.perex = perex;
    }

    public ProfileAttributes getProfileAttributes() {
        return profileAttributes;
    }

    public void setProfileAttributes(ProfileAttributes profileAttributes) {
        this.profileAttributes = profileAttributes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
