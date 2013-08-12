package org.lisak.pguide.model;

import com.googlecode.objectify.annotation.Embed;

/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 27.06.13
 * Time: 23:51
 */
@Embed
public class ProfileAttributes {
    private boolean freeWifi;
    private boolean smokers;
    private boolean nonsmokers;
    private boolean noCreditCards;

    public boolean isFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    public boolean isNoCreditCards() {
        return noCreditCards;
    }

    public void setNoCreditCards(boolean noCreditCards) {
        this.noCreditCards = noCreditCards;
    }

    public boolean isNonsmokers() {
        return nonsmokers;
    }

    public void setNonsmokers(boolean nonsmokers) {
        this.nonsmokers = nonsmokers;
    }

    public boolean isSmokers() {
        return smokers;
    }

    public void setSmokers(boolean smokers) {
        this.smokers = smokers;
    }
}