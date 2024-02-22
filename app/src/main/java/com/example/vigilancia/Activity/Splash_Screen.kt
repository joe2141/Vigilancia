package com.example.vigilancia.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.example.vigilancia.R

class Splash_Screen : Activity() {

    private val SPLASH_DISPLAY_LENGTH = 1000

    @RequiresApi(android.os.Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setTheme(R.style.MyNoActionBarShadowTheme)
        setContentView(R.layout.activity_splash_screen)

        // Set status bar color as blue
        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.rojoDebil)

        Handler().postDelayed({
            // Directly navigate to LoginActivity after SPLASH_DISPLAY_LENGTH
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}