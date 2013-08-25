package org.lisak.pguide.model;

public class MapMarker {
    private String lat;
    private String lng;
    private String name;
    private String title;

    public MapMarker(String lat, String lng, String name, String title) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
