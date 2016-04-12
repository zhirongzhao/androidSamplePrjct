package com.sample.androidsampleprjct.bcr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sample.androidsampleprjct.service.SSQService;

public class BCRBoot extends BroadcastReceiver {

    private static final String TAG = "BCRBoot_LOG";
    public BCRBoot() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        SSQService.startActionSSQ(context,1000*3600*24+"","");
    }
}
