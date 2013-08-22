package org.lisak.pguide.model;

import com.googlecode.objectify.annotation.EntitySubclass;
import geo.gps.Coordinates;

import java.util.Formatter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 16.08.13
 * Time: 17:55
 */
@EntitySubclass(index = true)
public class Profile extends Content {
    private String name;
    private String url;
    private String gpsCoords;
    private List<DayOpeningHours> openingHours;
    private String address;
    private String perex;
    private String text;
    private String category;
    private String prices;
    private ProfileAttributes attributes;
    private String profileImg;
    private List<String> gallery;

    public Profile() {
        attributes = new ProfileAttributes();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public String getGpsCoords() {
        return gpsCoords;
    }

    public void setGpsCoords(String strCoords) {
        //parse and normalize gps coords to decimal form

        Coordinates coords = new Coordinates();
        coords.parse(strCoords);

        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);
        fmt.format("%1$s %2$s", coords.getLatitude(), coords.getLongitude());
        this.gpsCoords = fmt.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DayOpeningHours> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<DayOpeningHours> openingHours) {
        this.openingHours = openingHours;
    }

    public String getPerex() {
        return perex;
    }

    public void setPerex(String perex) {
        this.perex = perex;
    }

    public ProfileAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ProfileAttributes attributes) {
        this.attributes = attributes;
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

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
