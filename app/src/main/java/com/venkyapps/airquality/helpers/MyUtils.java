package com.venkyapps.airquality.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.venkyapps.airquality.R;
import com.venkyapps.airquality.features.airquality.model.AirQualityResponseError;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class MyUtils {

    public static void showSnackBar(View view, String snackMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (view.isAttachedToWindow()) {
                Snackbar.make(view, snackMessage, Snackbar.LENGTH_LONG).show();
            }
            return;
        }
        Snackbar.make(view, snackMessage, Snackbar.LENGTH_LONG).show();
    }


    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void handleRetrofitFailure(View view, Throwable t) {
        String errorMessage = MyConstants.ERROR_SOMETHING_WENT_WRONG_TRY_AGAIN_LATER;
        if (t instanceof TimeoutException) {
            errorMessage = MyConstants.ERROR_TIMEOUT_EXCEPTION;
        } else if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
            errorMessage = MyConstants.ERROR_SOCKET_TIMEOUT_EXCEPTION;
        } else if (t instanceof ConnectException) {
            if (InternetConnectionDetector.isConnected(view.getContext())) {
                errorMessage = t.getMessage();
            } else {
                errorMessage = MyConstants.ERROR_NO_INTERNET_CONNECTION;
            }
        }
        showSnackBar(view, errorMessage);
    }

    public static void dialForCall(Context context, String dialNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + dialNumber));
        context.startActivity(dialIntent);
    }


    public static void showSnackBarWithAction(View view, String message, String actionMessage) {
        final Snackbar snackBar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        snackBar.setAction(actionMessage, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        });
        snackBar.show();
    }

    public static void showToast(Context context, String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
    }


    public static void handleResponseCode(View parentView, Retrofit retrofit, ResponseBody response) {
        try {
            Converter<ResponseBody, AirQualityResponseError> errorConverter =
                    retrofit.responseBodyConverter(AirQualityResponseError.class, new Annotation[0]);
            AirQualityResponseError error = errorConverter.convert(response);
            MyUtils.showSnackBar(parentView, error.getError().getMessage());
        } catch (IOException e) {
            MyUtils.showSnackBar(parentView, MyConstants.ERROR_SOMETHING_WENT_WRONG_TRY_AGAIN_LATER);
        }
    }

    /*
    public static String getAppPackageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            return Constants.INFO_APP_PACKAGE_NAME;
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return Constants.INFO_APP_VERSION_NAME;
        }
    }*/

    /*  public static void sendFeedback(Context context) {
          try {
              Intent fIntent = new Intent(Intent.ACTION_SENDTO);
              String uriText = "mailto:" + Uri.encode(Constants.SEND_FEEDBACK_EMAIL_ID) +
                      "?subject=" + Uri.encode(context.getResources().getString(R.string.app_name) + " app feedback- Reg") +
                      "&body=" + Uri.encode(Constants.SEND_FEEDBACK_EMAIL_MESSAGE);
              Uri uri = Uri.parse(uriText);
              fIntent.setData(uri);
              context.startActivity(Intent.createChooser(fIntent, Constants.SEND_FEEDBACK_CREATOR_TITLE));
          } catch (Exception ignored) {
          }
      }

  */
    public static void shareThisApp(Context context, String shareContent) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType(MyConstants.INTENT_TYPE_TEXT_PLAIN);
        i.putExtra(Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.app_name) + " app");
        i.putExtra(Intent.EXTRA_TEXT, shareContent);
        context.startActivity(Intent.createChooser(i, MyConstants.SHARE_THIS_APP_CREATOR_TITLE));
    }

}