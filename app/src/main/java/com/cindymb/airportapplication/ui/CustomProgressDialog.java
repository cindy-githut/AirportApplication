package com.cindymb.airportapplication.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ProgressBar;

import com.cindymb.airportapplication.R;


public class CustomProgressDialog {

    private Dialog mDialog;
    private Activity mActivity;

    public CustomProgressDialog(Context context) {
        mDialog = new Dialog(context);
        Window window = mDialog.getWindow();
        mActivity = (Activity) context;

        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_progress_bar);
        mDialog.setCancelable(false);
        ProgressBar progressBar = mDialog.findViewById(R.id.mainProgressBar);

        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        if (progressBar != null && progressBar.getIndeterminateDrawable() != null) {
            progressBar.getIndeterminateDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    /**
     * Shows the progress dialog if the dialog is not currently displayed
     */
    public void showProgress() {
        if (mDialog != null && !mDialog.isShowing() && !mActivity.isDestroyed() && !mActivity.isFinishing()) {
            mDialog.show();
        }
    }

    /**
     * Hides the progress dialog if the dialog is currently displayed
     */
    public void dismissProgress() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
