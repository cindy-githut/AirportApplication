package com.cindymb.airportapplication.base;

import com.google.gson.annotations.SerializedName;

public class ErrorText {
    @SerializedName("text")
    private String stringError;

    public String getStringError() {
        return stringError;
    }

    public void setStringError(String stringError) {
        this.stringError = stringError;
    }
}
