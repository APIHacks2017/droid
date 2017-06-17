package com.venkyapps.airquality.helpers;

/**
 * Created by Venkatesh on 17-Jun-17.
 */

import android.app.ProgressDialog;
import android.content.Context;

public class ApiProgressDialog {

    private ProgressDialog progressDialog;

    public ApiProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    public void show(String title, String message) {
        dismiss();
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismiss() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}