package com.example.bttest

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MainActivity : AppCompatActivity() {
    //    private val filterActivityLauncher: ActivityResultLauncher<Intent> =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            handleSelectedFilterItems(it)
//        }
    private val mReceiver = TestBroadcastReceiver()
    private final val mACTION = "com.example.bttest.MY_NOTIFICATION"

    val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            Log.d("MainActivity", "Intent: $intent")
            when (intent?.action) {
                //BROADCAST_DEFAULT_ALBUM_CHANGED -> handleAlbumChanged()
                //BROADCAST_CHANGE_TYPE_CHANGED -> handleChangeTypeChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var iFilter = IntentFilter()
        iFilter.addAction(mACTION)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mReceiver, iFilter)

//        Intent().also { intent ->
//            intent.action = mACTION
//            intent.putExtra("data", "Notice me senpai!")
//            Log.d("MainActivity", "Intent: $intent")
//            sendBroadcast(intent)
//        }


        val txtView = findViewById<TextView>(R.id.textView)
        val btnBr = findViewById<Button>(R.id.button)
        btnBr.setOnClickListener {
            txtView.text = "aaa"
            // val myIntent = Intent(this, JoinActivity::class.java)

            // ?????? ????????? ????????? ????????? ??????
            // val inputMsg = txtView.text.toString()

            // ????????? ??? ???????????? putExtra??? ??????
            // myIntent.putExtra("message", inputMsg)

            // JoinActivity ??? ?????? ??????
            // startActivity(myIntent)
            // startActivityForResult(myIntent, 200)

            // sendBroadcast
//            var intent = Intent()
//            intent.setAction(mACTION)
//            intent.putExtra("data", "Notice me senpai!")
//            Log.d("MainActivity", "Intent: $intent")
//            sendBroadcast(intent)

            Toast.makeText(this, "GOOOOOOOD", Toast.LENGTH_LONG).show()

            Intent().also { intent ->
                intent.action = mACTION
                intent.putExtra("data", "Notice me senpai!")
                Log.d("MainActivity", "Intent: $intent")
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            }


//            val filter = IntentFilter(android.intent.action.SEND).apply {
//                addAction("example.obigo.brbr")
//            }

//            val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
//                addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
//             }

//            Intent().also { intent ->
//                intent.action = "example.obigo.brbr"
//                sendBroadcast(intent)
//            }

//
//            var toast = Toast.makeText(this, "start", Toast.LENGTH_LONG)
//            toast.show()

        }
    }

    // ????????? ????????? ??? ???????????? ?????? ???????????????
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val txtView = findViewById<TextView>(R.id.textView)
        // resultCode??? ?????? ?????????????????????.
        // ????????? ???????????? ??? 200????????? resultCode??? ??????????????? ?????? ?????? ????????? ??????
        if (requestCode == 200) {
            //setResult(Activity.RESULT_OK, resultsIntent) ?????? ???????????? result_OK ?????? ?????????
            if (resultCode == Activity.RESULT_OK) {
                // ?????? ?????? ????????? ?????? ????????? ??????????????? text??????
                val newNickName = data?.getStringExtra("newNickname")
                // ??????????????? ?????? ????????? TextView ??????
                txtView.text = newNickName
            }
        }
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(mReceiver)
        super.onDestroy()
    }


}

//class ScreenBroadcastReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        if(intent.getAction().equals("BRBR")){
//            var toast = Toast.makeText(context, "good", Toast.LENGTH_LONG)
//            toast.show()
//        }
//    }
//}