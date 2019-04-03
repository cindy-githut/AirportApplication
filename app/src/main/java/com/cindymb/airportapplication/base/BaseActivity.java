package com.cindymb.airportapplication.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.utils.LoggingHelper;

import dagger.android.AndroidInjection;

public class BaseActivity extends AppCompatActivity {

    public static final String RECEIVER_PROGRESS_DIALOG = "RECEIVER_PROGRESS_DIALOG";
    public static final String INTENT_SHOW_PROGRESS = "INTENT_SHOW_PROGRESS";
    public static final String RECEIVER_ERROR_DIALOG = "RECEIVER_ERROR_DIALOG";
    public static final String INTENT_MESSAGE = "INTENT_MESSAGE";
    private CustomProgressDialog mCustomCustomProgressDialog;

    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String filter = intent.getAction();
            if (TextUtils.isEmpty(filter)) return;

            switch (filter) {
                case RECEIVER_ERROR_DIALOG:
                    displayDialog(intent.getStringExtra(INTENT_MESSAGE));
                    break;
                case RECEIVER_PROGRESS_DIALOG:
                    boolean showProgress = intent.getBooleanExtra(INTENT_SHOW_PROGRESS, false);
                    if (showProgress) {
                        mCustomCustomProgressDialog.showProgress();
                    } else {
                        mCustomCustomProgressDialog.dismissProgress();
                    }
                    break;

            }
        }
    };

    /**
     * Checks for Network & Internet Connectivity
     *
     * @param mActivity current context
     * @return true if internet available
     */
    public static boolean isConnected(Context mActivity) {
        try {
            ConnectivityManager cm = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            AndroidInjection.inject(this);
        } catch (IllegalArgumentException aE) {
            LoggingHelper.error(BaseActivity.class, "Please add " + this.getClass().getSimpleName() + " to ActivityModule.class:\nError: " + aE.getMessage());
        }
        super.onCreate(savedInstanceState);
        mCustomCustomProgressDialog = new CustomProgressDialog(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, getIntentFilters());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    /**
     * Returns a list of actions on an Intent Filter for use by the LocalBroadcastManager
     *
     * @return - Populated IntentFilter with all the actions attached
     */
    private IntentFilter getIntentFilters() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RECEIVER_PROGRESS_DIALOG);
        intentFilter.addAction(RECEIVER_ERROR_DIALOG);
        return intentFilter;
    }


    /**
     * Displays a dialog with no actions
     *
     * @param aMessage - Message to display
     */
    public void displayDialog(String aMessage) {
        displayDialog(aMessage, getString(R.string.lbl_ok), null, true);
    }

    /**
     * Method to display a dialog with  positive action
     *
     * @param aMessage         - Message to display
     * @param aPositiveText    - Positive Text eg YES / Okay
     * @param aPositiveOnClick - Positive on click action
     * @param cancellable      - Set the dialog to be cancellable when tapping outside the dialog
     */
    public void displayDialog(String aMessage, String aPositiveText, DialogInterface.OnClickListener aPositiveOnClick, boolean cancellable) {
        if (TextUtils.isEmpty(aMessage)) {
            aMessage = getString(R.string.msg_generic_error);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setMessage(aMessage);

        if (!TextUtils.isEmpty(aPositiveText)) {
            builder.setPositiveButton(aPositiveText, aPositiveOnClick);
        }
        builder.setCancelable(cancellable);
        AlertDialog alert = builder.create();
        alert.show();
        Button positiveButton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.BLACK);
        Button negativeButton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.BLACK);
    }
}
