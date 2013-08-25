package org.lisak.pguide.model;

import geo.gps.Coordinates;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 23.08.13
 * Time: 23:54
 *
 * Class that agregates attributes necessary for JSON POI generation
 */
public class MapMarkersCategory {
    private String categoryName;
    private String icon;
    private ArrayList<MapMarker> items;

    public MapMarkersCategory() {
        this.items = new ArrayList<MapMarker>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArrayList<MapMarker> getItems() {
        return items;
    }

    public void setItems(ArrayList<MapMarker> items) {
        this.items = items;
    }

    public void addItem(Profile profile) {
        Coordinates coord = new Coordinates();
        coord.parse(profile.getGpsCoords());
        MapMarker mm = new MapMarker(coord.getLatitude().toString(),
                coord.getLongitude().toString(),
                profile.getId(), profile.getName());
        items.add(mm);
    }
}
