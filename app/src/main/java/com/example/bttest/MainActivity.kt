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

            // 우선 들고갈 메시지 변수에 담기
            // val inputMsg = txtView.text.toString()

            // 가지고 갈 메시지를 putExtra에 담기
            // myIntent.putExtra("message", inputMsg)

            // JoinActivity 로 화면 이동
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

    // 결과를 받아올 때 실행되는 함수 오버라이딩
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val txtView = findViewById<TextView>(R.id.textView)
        // resultCode를 우선 검사해야합니다.
        // 화면에 들어가기 전 200이라는 resultCode를 주어줬는데 해당 값이 맞는지 체크
        if (requestCode == 200) {
            //setResult(Activity.RESULT_OK, resultsIntent) 에서 부여했던 result_OK 값이 맞는지
            if (resultCode == Activity.RESULT_OK) {
                // 모든 것이 맞으면 새로 변경한 닉네임으로 text반영
                val newNickName = data?.getStringExtra("newNickname")
                // 메인화면에 있는 닉네임 TextView 변경
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