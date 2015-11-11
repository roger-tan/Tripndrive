package fr.rogertan.challenge.tripndrive.models;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rogertan on 10/11/15.
 */
public class Image implements Serializable{

    private String mediumUrl;
    private String key;
    private String smallUrl;
    private String largeUrl;


    public Image () {

    }

    public Image (JSONObject json) {

        this.mediumUrl = json.optString("mediumUrl");
        this.key = json.optString("key");
        this.smallUrl = json.optString("smallUrl");
        this.largeUrl = json.optString("largeUrl");

    }

    public String getMediumUrl() {
        return this.mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSmallUrl() {
        return this.smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getLargeUrl() {
        return this.largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }



}
