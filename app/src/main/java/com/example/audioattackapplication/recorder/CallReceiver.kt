package com.example.audioattackapplication.recorder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        var callInProgress = false;
        // Check call state (RINGING, OFFHOOK, IDLE)
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        if (state != null) {
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
            } else if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                callInProgress = true;
            } else if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                callInProgress = false;
            }
        }
    }
}