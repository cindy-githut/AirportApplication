package com.cindymb.airportapplication.ui.nearby.model;

import com.google.gson.annotations.SerializedName;

public class NearbyAirportModel {

    @SerializedName("nameAirport")
    public String nameAirport;

    @SerializedName("codeIataAirport")
    public String codeIataAirport;

    @SerializedName("codeIcaoAirport")
    public String codeIcaoAirport;

    @SerializedName("nameTranslations")
    public String nameTranslations;

    @SerializedName("latitudeAirport")
    public String latitudeAirport;

    @SerializedName("longitudeAirport")
    public String longitudeAirport;

    @SerializedName("timezone")
    public String timezone;

    @SerializedName("GMT")
    public String GMT;

    @SerializedName("phone")
    public String phone;

    @SerializedName("nameCountry")
    public String nameCountry;

    @SerializedName("codeIso2Country")
    public String codeIso2Country;

    @SerializedName("codeIataCity")
    public String codeIataCity;

    @SerializedName("distance")
    public String distance;


    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public String getCodeIataAirport() {
        return codeIataAirport;
    }

    public void setCodeIataAirport(String codeIataAirport) {
        this.codeIataAirport = codeIataAirport;
    }

    public String getCodeIcaoAirport() {
        return codeIcaoAirport;
    }

    public void setCodeIcaoAirport(String codeIcaoAirport) {
        this.codeIcaoAirport = codeIcaoAirport;
    }

    public String getNameTranslations() {
        return nameTranslations;
    }

    public void setNameTranslations(String nameTranslations) {
        this.nameTranslations = nameTranslations;
    }

    public String getLatitudeAirport() {
        return latitudeAirport;
    }

    public void setLatitudeAirport(String latitudeAirport) {
        this.latitudeAirport = latitudeAirport;
    }

    public String getLongitudeAirport() {
        return longitudeAirport;
    }

    public void setLongitudeAirport(String longitudeAirport) {
        this.longitudeAirport = longitudeAirport;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
