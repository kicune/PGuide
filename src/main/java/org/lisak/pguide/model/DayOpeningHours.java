package org.lisak.pguide.model;

import com.googlecode.objectify.annotation.Embed;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 15.08.13
 * Time: 9:37
 */
@Embed
public class DayOpeningHours {
    private String morning;
    private String afternoon;

    //opening hours "" - "" -> closed

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }


}