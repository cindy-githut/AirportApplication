package com.cindymb.airportapplication.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponseModel {

    @SerializedName("error")
    private ErrorText error;

    public ErrorText getError() {
        return error;
    }

    public void setError(ErrorText error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseResponseModel{" +
                "error=" + error +
                '}';
    }
}
