package com.adnankhan.assignment.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.adnankhan.assignment.MainActivity

class ReceiverBootComplete : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            MainActivity.scheduleNotification(context)
        }
    }
}