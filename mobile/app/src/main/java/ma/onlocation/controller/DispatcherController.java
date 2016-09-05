package ma.onlocation.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import ma.onlocation.activity.LoginActivity;

/**
 * Created by kb50_000 on 08/05/2015.
 */
public class DispatcherController {
    Activity activity;
    Intent intent;
    Context context;

    public DispatcherController(Activity activity, Intent intent, Context context) {
        this.activity = activity;
        this.intent = intent;
        this.context = context;
    }

    public void doDispatcher() {
        if (!getIsAuthentificated()) {
            intent.setClass(context, LoginActivity.class);
        }

        activity.startActivity(intent);
        activity.finish();
    }

    public Boolean getIsAuthentificated() {
        return false;
    }


}
