package com.cindymb.airportapplication.ui.cities;

import com.google.gson.annotations.SerializedName;

public class CitiesModel {

    @SerializedName("nameTranslations")
    public String nameTranslations;

    @SerializedName("timezone")
    public String timezone;

    @SerializedName("GMT")
    public String GMT;

    @SerializedName("codeIso2Country")
    public String codeIso2Country;

    @SerializedName("codeIataCity")
    public String codeIataCity;

    @SerializedName("cityId")
    public String cityId;

    @SerializedName("nameCity")
    public String nameCity;

    @SerializedName("latitudeCity")
    public String latitudeCity;

    @SerializedName("longitudeCity")
    public String longitudeCity;

    @SerializedName("geonameId")
    public String geonameId;

    public String getNameTranslations() {
        return nameTranslations;
    }

    public void setNameTranslations(String nameTranslations) {
        this.nameTranslations = nameTranslations;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getGMT() {
        return GMT;
    }

    public void setGMT(String GMT) {
        this.GMT = GMT;
    }

    public String getCodeIso2Country() {
        return codeIso2Country;
    }

    public void setCodeIso2Country(String codeIso2Country) {
        this.codeIso2Country = codeIso2Country;
    }

    public String getCodeIataCity() {
        return codeIataCity;
    }

    public void setCodeIataCity(String codeIataCity) {
        this.codeIataCity = codeIataCity;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getLatitudeCity() {
        return latitudeCity;
    }

    public void setLatitudeCity(String latitudeCity) {
        this.latitudeCity = latitudeCity;
    }

    public String getLongitudeCity() {
        return longitudeCity;
    }

    public void setLongitudeCity(String longitudeCity) {
        this.longitudeCity = longitudeCity;
    }

    public String getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(String geonameId) {
        this.geonameId = geonameId;
    }
}
