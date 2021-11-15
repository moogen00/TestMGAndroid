package com.example.bttest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.bttest.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_join)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // 액티비티에 들어오자마자 바로 첨부된 값을 받아서 텍스트뷰에 반영
        val receivedMessage = intent.getStringExtra("message")
        val txtFirst = findViewById<TextView>(R.id.textview_first)
        txtFirst.text = receivedMessage

        // finish when touched email icon
        binding.fab.setOnClickListener {

            // 돌아갈 때 첨부할 데이터를 들고 있어주는 Intent와는 별개로 생성
            val resultsIntent = Intent()
            // 입력한 닉네임을 resultsIntent에 첨부 = putExtra
            val input = "result text"
            resultsIntent.putExtra("newNickname", input)
            // OK 버튼을 눌렀을 때 데이터 담고있는 resultsIntent를 갖고 복귀처리
            // 기존에는 그냥 finish()처리만 해줬지만 이번에는
            // 결과값을 들고 가야하기에 꼭 필요
            // public static final int RESULT_OK = -1;
            setResult(Activity.RESULT_OK, resultsIntent)

            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_join)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}




//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_join)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
// }