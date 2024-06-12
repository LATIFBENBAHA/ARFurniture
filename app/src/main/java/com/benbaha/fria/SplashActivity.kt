package com.benbaha.fria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.benbaha.fria.Utils

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        supportActionBar?.hide()

        Handler().postDelayed({
            /*val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)*/
            val token = this?.getSharedPreferences("my_app", Context.MODE_PRIVATE)?.getString(
                Utils.TOKEN, null)
            val expiryTime = this?.getSharedPreferences("my_app", Context.MODE_PRIVATE)?.getLong(
                Utils.EXPIRY_TIME.toString(), 0)
            if (token != null && expiryTime!=null && System.currentTimeMillis() < expiryTime) {
                // Token is still valid, take user to the next activity
                val intent = Intent(this, OpenCameraActivity::class.java)
                startActivity(intent)
                this.finish()
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.finish()
            }
            /*val intent = Intent(this, OpenCameraActivity::class.java)
            startActivity(intent)
            finish()*/
        },1300)
    }
}