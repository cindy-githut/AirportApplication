package com.cindymb.airportapplication.base;

import android.app.Application;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.cindymb.airportapplication.MainActivity;

import javax.inject.Inject;

import retrofit2.Response;

public class BaseRepository {
    private final LocalBroadcastManager mLocalBroadcastManager;

    @Inject
    public BaseRepository(Application aApplication) {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(aApplication);
    }

    public void showProgressDialog(boolean shouldShow) {
        Intent intent = new Intent(MainActivity.RECEIVER_PROGRESS_DIALOG);
        intent.putExtra(MainActivity.INTENT_SHOW_PROGRESS, shouldShow);
        mLocalBroadcastManager.sendBroadcast(intent);
    }


    public <T extends BaseResponseModel> void handleSuccessResponse(Response<T> response) {
        showProgressDialog(false);
        if (response == null || response.body() == null) {
            sendGenericErrorMessage();
            return;
        }
        try {
            if (!TextUtils.isEmpty(response.body().getError().getStringError())) {
                sendErrorMessage(response.body().getError().getStringError());
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorMessage(e.getMessage());
        }
    }

    public void handleErrorResponse(Throwable t) {
        showProgressDialog(false);
        sendErrorMessage(t.getLocalizedMessage());
    }

    public void sendGenericErrorMessage() {
        sendErrorMessage("");
    }

    protected void sendErrorMessage(String aErrorMessage) {
        Intent intent = new Intent(MainActivity.RECEIVER_ERROR_DIALOG);
        intent.putExtra(MainActivity.INTENT_MESSAGE, aErrorMessage);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    public <T extends BaseResponseModel> boolean isResponseSuccessful(T model) {

        return TextUtils.isEmpty(model.getError().getStringError());
    }

}