package com.cindymb.airportapplication.services.repositories;

import android.app.Application;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.ui.MainActivity;
import com.cindymb.airportapplication.ui.base.BaseResponseModel;
import com.cindymb.airportapplication.ui.nearby.model.NearbyAirportModel;
import com.cindymb.airportapplication.ui.schedule.model.FlightScheduleModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class BaseRepository {
    private final LocalBroadcastManager mLocalBroadcastManager;
    private Application mApplication;

    @Inject
    public BaseRepository(Application aApplication) {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(aApplication);
        mApplication = aApplication;
    }

    void showProgressDialog(boolean shouldShow) {
        Intent intent = new Intent(MainActivity.RECEIVER_PROGRESS_DIALOG);
        intent.putExtra(MainActivity.INTENT_SHOW_PROGRESS, shouldShow);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    void handleJSONObjectResponse(Response<Object> response, String stringGson) throws JSONException {

        if (response.body() == null) return;
        JSONObject jsonO = new JSONObject(stringGson);
        BaseResponseModel model = new Gson().fromJson(jsonO.toString(), new TypeToken<BaseResponseModel>() {
        }.getType());
        handleErrorResponse(model);

    }

    List<FlightScheduleModel> handleJSONArrayResponseSchedule(Response<Object> response, String stringGson) throws JSONException {

        if (response.body() == null) return null;
        showProgressDialog(false);

        JSONArray jsonArray = new JSONArray(stringGson);

        return new Gson().fromJson(jsonArray.toString(), new TypeToken<List<FlightScheduleModel>>() {
        }.getType());
    }

    List<NearbyAirportModel> handleJSONArrayResponse(Response<Object> response, String stringGson) throws JSONException {

        if (response.body() == null) return null;
        showProgressDialog(false);

        JSONArray jsonArray = new JSONArray(stringGson);

        return new Gson().fromJson(jsonArray.toString(), new TypeToken<List<NearbyAirportModel>>() {
        }.getType());
    }

    private void handleErrorResponse(BaseResponseModel baseResponseModel) {
        showProgressDialog(false);
        if (baseResponseModel == null || baseResponseModel.getError() == null) {
            sendGenericErrorMessage();
            return;
        }
        try {
            if (!TextUtils.isEmpty(baseResponseModel.getError().getStringError())) {
                sendErrorMessage(baseResponseModel.getError().getStringError());
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorMessage(e.getMessage());
        }
    }

    void handleFailedResponse(Throwable t) {
        showProgressDialog(false);
        sendErrorMessage(t.getLocalizedMessage());
    }

    private void sendGenericErrorMessage() {
        sendErrorMessage(mApplication.getString(R.string.msg_generic_error));
    }

    private void sendErrorMessage(String aErrorMessage) {
        Intent intent = new Intent(MainActivity.RECEIVER_ERROR_DIALOG);
        intent.putExtra(MainActivity.INTENT_MESSAGE, aErrorMessage);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

}
