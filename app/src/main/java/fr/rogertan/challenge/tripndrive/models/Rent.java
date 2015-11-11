package fr.rogertan.challenge.tripndrive.models;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rogertan on 10/11/15.
 */

public class Rent implements Serializable{

    private boolean airconditionning;
    private String gazLabel;
    private String modelBrand;
    private String kilometerLabel;
    private double placeNumber;
    private double childSeat;
    private double days;
    private String modelName;
    private String transmissionLabel;
    private String gaz;
    private List<Image> images;
    private double dailyPrice;
    private String transmission;
    private String modelCategory;
    private double extraKmPrice;
    private double babyChair;
    private double doorNumber;
    private double kmIncluded;
    private double boosterSeat;
    private boolean jackPlug;
    private String end;
    private double pid;
    private double carYear;
    private String start;
    private double price;
    private boolean sunroof;
    private String modelCategoryLabel;
    private boolean gps;


    public Rent () {

    }

    public Rent (JSONObject json) {

        this.airconditionning = json.optBoolean("airconditionning");
        this.gazLabel = json.optString("gazLabel");
        this.modelBrand = json.optString("modelBrand");
        this.kilometerLabel = json.optString("kilometerLabel");
        this.placeNumber = json.optDouble("placeNumber");
        this.childSeat = json.optDouble("childSeat");
        this.days = json.optDouble("days");
        this.modelName = json.optString("modelName");
        this.transmissionLabel = json.optString("transmissionLabel");
        this.gaz = json.optString("gaz");

        this.images = new ArrayList<Image>();
        JSONArray arrayImages = json.optJSONArray("images");
        if (null != arrayImages) {
            int imagesLength = arrayImages.length();
            for (int i = 0; i < imagesLength; i++) {
                JSONObject item = arrayImages.optJSONObject(i);
                if (null != item) {
                    this.images.add(new Image(item));
                }
            }
        }
        else {
            JSONObject item = json.optJSONObject("images");
            if (null != item) {
                this.images.add(new Image(item));
            }
        }

        this.dailyPrice = json.optDouble("dailyPrice");
        this.transmission = json.optString("transmission");
        this.modelCategory = json.optString("modelCategory");
        this.extraKmPrice = json.optDouble("extraKmPrice");
        this.babyChair = json.optDouble("babyChair");
        this.doorNumber = json.optDouble("doorNumber");
        this.kmIncluded = json.optDouble("kmIncluded");
        this.boosterSeat = json.optDouble("boosterSeat");
        this.jackPlug = json.optBoolean("jackPlug");
        this.end = json.optString("end");
        this.pid = json.optDouble("pid");
        this.carYear = json.optDouble("carYear");
        this.start = json.optString("start");
        this.price = json.optDouble("price");
        this.sunroof = json.optBoolean("sunroof");
        this.modelCategoryLabel = json.optString("modelCategoryLabel");
        this.gps = json.optBoolean("gps");

    }

    public boolean getAirconditionning() {
        return this.airconditionning;
    }

    public void setAirconditionning(boolean airconditionning) {
        this.airconditionning = airconditionning;
    }

    public String getGazLabel() {
        return this.gazLabel;
    }

    public void setGazLabel(String gazLabel) {
        this.gazLabel = gazLabel;
    }

    public String getModelBrand() {
        return this.modelBrand;
    }

    public void setModelBrand(String modelBrand) {
        this.modelBrand = modelBrand;
    }

    public String getKilometerLabel() {
        return this.kilometerLabel;
    }

    public void setKilometerLabel(String kilometerLabel) {
        this.kilometerLabel = kilometerLabel;
    }

    public double getPlaceNumber() {
        return this.placeNumber;
    }

    public void setPlaceNumber(double placeNumber) {
        this.placeNumber = placeNumber;
    }

    public double getChildSeat() {
        return this.childSeat;
    }

    public void setChildSeat(double childSeat) {
        this.childSeat = childSeat;
    }

    public double getDays() {
        return this.days;
    }

    public void setDays(double days) {
        this.days = days;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTransmissionLabel() {
        return this.transmissionLabel;
    }

    public void setTransmissionLabel(String transmissionLabel) {
        this.transmissionLabel = transmissionLabel;
    }

    public String getGaz() {
        return this.gaz;
    }

    public void setGaz(String gaz) {
        this.gaz = gaz;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public double getDailyPrice() {
        return this.dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public String getTransmission() {
        return this.transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getModelCategory() {
        return this.modelCategory;
    }

    public void setModelCategory(String modelCategory) {
        this.modelCategory = modelCategory;
    }

    public double getExtraKmPrice() {
        return this.extraKmPrice;
    }

    public void setExtraKmPrice(double extraKmPrice) {
        this.extraKmPrice = extraKmPrice;
    }

    public double getBabyChair() {
        return this.babyChair;
    }

    public void setBabyChair(double babyChair) {
        this.babyChair = babyChair;
    }

    public double getDoorNumber() {
        return this.doorNumber;
    }

    public void setDoorNumber(double doorNumber) {
        this.doorNumber = doorNumber;
    }

    public double getKmIncluded() {
        return this.kmIncluded;
    }

    public void setKmIncluded(double kmIncluded) {
        this.kmIncluded = kmIncluded;
    }

    public double getBoosterSeat() {
        return this.boosterSeat;
    }

    public void setBoosterSeat(double boosterSeat) {
        this.boosterSeat = boosterSeat;
    }

    public boolean getJackPlug() {
        return this.jackPlug;
    }

    public void setJackPlug(boolean jackPlug) {
        this.jackPlug = jackPlug;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getPid() {
        return this.pid;
    }

    public void setPid(double pid) {
        this.pid = pid;
    }

    public double getCarYear() {
        return this.carYear;
    }

    public void setCarYear(double carYear) {
        this.carYear = carYear;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getSunroof() {
        return this.sunroof;
    }

    public void setSunroof(boolean sunroof) {
        this.sunroof = sunroof;
    }

    public String getModelCategoryLabel() {
        return this.modelCategoryLabel;
    }

    public void setModelCategoryLabel(String modelCategoryLabel) {
        this.modelCategoryLabel = modelCategoryLabel;
    }

    public boolean getGps() {
        return this.gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public String toString() {
        return modelBrand + " " + modelName + " (" + carYear + ", " + kilometerLabel + ")";
    }


}
