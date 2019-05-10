package com.cindymb.airportapplication.ui.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponseModel {

    @SerializedName("error")
    private BaseErrorTextResponse error;

    public BaseErrorTextResponse getError() {
        return error;
    }

    public void setError(BaseErrorTextResponse error) {
        this.error = error;
    }

}
