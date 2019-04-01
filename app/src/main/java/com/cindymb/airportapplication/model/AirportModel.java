package com.cindymb.airportapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirportModel {

    @Expose
    @SerializedName("nameAirport")
    public String nameAirport;

    @Expose
    @SerializedName("codeIataAirport")
    public String codeIataAirport;

    @Expose
    @SerializedName("codeIcaoAirport")
    public String codeIcaoAirport;

    @Expose
    @SerializedName("nameTranslations")
    public String nameTranslations;

    @Expose
    @SerializedName("latitudeAirport")
    public double latitudeAirport;

    @Expose
    @SerializedName("longitudeAirport")
    public double longitudeAirport;

    @Expose
    @SerializedName("timezone")
    public String timezone;

    @Expose
    @SerializedName("GMT")
    public String GMT;

    @Expose
    @SerializedName("phone")
    public String phone;

    @Expose
    @SerializedName("nameCountry")
    public String nameCountry;

    @Expose
    @SerializedName("codeIso2Country")
    public String codeIso2Country;

    @Expose
    @SerializedName("codeIataCity")
    public String codeIataCity;

    @Expose
    @SerializedName("distance")
    public double distance;


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

    public double getLatitudeAirport() {
        return latitudeAirport;
    }

    public void setLatitudeAirport(double latitudeAirport) {
        this.latitudeAirport = latitudeAirport;
    }

    public double getLongitudeAirport() {
        return longitudeAirport;
    }

    public void setLongitudeAirport(double longitudeAirport) {
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
