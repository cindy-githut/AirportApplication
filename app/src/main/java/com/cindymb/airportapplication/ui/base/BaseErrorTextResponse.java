package com.cindymb.airportapplication.ui.base;

import com.google.gson.annotations.SerializedName;

public class BaseErrorTextResponse {
    @SerializedName("text")
    private String stringError;

    public String getStringError() {
        return stringError;
    }

    public void setStringError(String stringError) {
        this.stringError = stringError;
    }
}
