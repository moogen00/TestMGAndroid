package com.example.bttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class TestBroadcastReceiver : BroadcastReceiver()  {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("TestBroadcastReceiver", "onReceive")
        if (intent?.action.equals("com.example.bttest.MY_NOTIFICATION")) {

            Log.d("TestBroadcastReceiver", "Intent: $intent")
            var toast = Toast.makeText(context, "good", Toast.LENGTH_LONG).show()
        }
    }


}