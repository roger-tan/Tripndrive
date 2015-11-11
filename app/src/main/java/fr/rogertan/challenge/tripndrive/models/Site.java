package fr.rogertan.challenge.tripndrive.models;

/**
 * Created by rogertan on 10/11/15.
 */
import org.json.*;

import java.io.Serializable;


public class Site implements Serializable{

    private String agencyStart;
    private String label;
    private double rentDelayHours;
    private String type;
    private String code;
    private double lat;
    private String htmlParkLandingUrl;
    private String agencyEnd;
    private double lng;
    private double parkDelayHours;
    private double parkMinDurationHours;
    private double parkMaxDurationDays;


    public Site () {

    }

    public Site (JSONObject json) {

        this.agencyStart = json.optString("agencyStart");
        this.label = json.optString("label");
        this.rentDelayHours = json.optDouble("rentDelayHours");
        this.type = json.optString("type");
        this.code = json.optString("code");
        this.lat = json.optDouble("lat");
        this.htmlParkLandingUrl = json.optString("htmlParkLandingUrl");
        this.agencyEnd = json.optString("agencyEnd");
        this.lng = json.optDouble("lng");
        this.parkDelayHours = json.optDouble("parkDelayHours");
        this.parkMinDurationHours = json.optDouble("parkMinDurationHours");
        this.parkMaxDurationDays = json.optDouble("parkMaxDurationDays");

    }

    public String getAgencyStart() {
        return this.agencyStart;
    }

    public void setAgencyStart(String agencyStart) {
        this.agencyStart = agencyStart;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getRentDelayHours() {
        return this.rentDelayHours;
    }

    public void setRentDelayHours(double rentDelayHours) {
        this.rentDelayHours = rentDelayHours;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getHtmlParkLandingUrl() {
        return this.htmlParkLandingUrl;
    }

    public void setHtmlParkLandingUrl(String htmlParkLandingUrl) {
        this.htmlParkLandingUrl = htmlParkLandingUrl;
    }

    public String getAgencyEnd() {
        return this.agencyEnd;
    }

    public void setAgencyEnd(String agencyEnd) {
        this.agencyEnd = agencyEnd;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getParkDelayHours() {
        return this.parkDelayHours;
    }

    public void setParkDelayHours(double parkDelayHours) {
        this.parkDelayHours = parkDelayHours;
    }

    public double getParkMinDurationHours() {
        return this.parkMinDurationHours;
    }

    public void setParkMinDurationHours(double parkMinDurationHours) {
        this.parkMinDurationHours = parkMinDurationHours;
    }

    public double getParkMaxDurationDays() {
        return this.parkMaxDurationDays;
    }

    public void setParkMaxDurationDays(double parkMaxDurationDays) {
        this.parkMaxDurationDays = parkMaxDurationDays;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
