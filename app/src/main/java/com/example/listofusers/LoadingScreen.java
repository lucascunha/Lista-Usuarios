package com.example.listofusers;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingScreen {

    private Activity activity;
    private AlertDialog dialog;

    LoadingScreen(Activity myActivity) {
        activity = myActivity;
    }

    void startLoadingScreen() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_screen, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    void dismissLoading() {
        dialog.dismiss();
    }
}
