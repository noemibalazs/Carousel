package com.example.carousel.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            launch()
        }, 5000)
    }

    private fun launch(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
