package com.cindymb.airportapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;

import com.cindymb.airportapplication.base.CustomProgressDialog;
import com.cindymb.airportapplication.utils.LoggingHelper;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

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
            if (TextUtils.isEmpty(filter)) {
                return;
            }
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomCustomProgressDialog = new CustomProgressDialog(this);

        try {
            AndroidInjection.inject(this);
        } catch (IllegalArgumentException aE) {
            LoggingHelper.error(MainActivity.class, "Please add " + this.getClass().getSimpleName() + " to ActivityBuilder.class:\nError: " + aE.getMessage());
        }
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
     * Displays a dialog with no actions
     *
     * @param aMessage - Message to display
     */
    public void displayDialog(String aMessage) {
        displayDialog(aMessage, getString(R.string.lbl_ok), null, null, null, true);
    }

    /**
     * Method to display a dialog with both positive and negative actions
     *
     * @param aMessage         - Message to display
     * @param aPositiveText    - Positive Text eg YES / Okay
     * @param aPositiveOnClick - Positive on click action
     * @param aNegativeOnClick - Negative on click action
     * @param aNegativeText    - Negative text eg NO / Cancel
     * @param cancellable      - Set the dialog to be cancellable when tapping outside the dialog
     */
    public void displayDialog(String aMessage, String aPositiveText, DialogInterface.OnClickListener aPositiveOnClick, String aNegativeText, DialogInterface.OnClickListener aNegativeOnClick, boolean cancellable) {
        if (TextUtils.isEmpty(aMessage)) {
            aMessage = getString(R.string.lbl_generic_error);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setMessage(aMessage);
        if (!TextUtils.isEmpty(aNegativeText)) {
            if (aNegativeOnClick == null) {
                aNegativeOnClick = (dialog, which) -> {
                    // Do nothing
                };
            }
            builder.setNegativeButton(aNegativeText, aNegativeOnClick);
        }
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
