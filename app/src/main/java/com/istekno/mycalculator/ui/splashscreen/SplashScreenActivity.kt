package com.istekno.mycalculator.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.istekno.mycalculator.ui.calculator.CalculatorActivity
import com.istekno.mycalculator.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )

        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        )

        Handler(mainLooper).postDelayed(
            {
                startActivity(Intent(this, CalculatorActivity::class.java))
                finish()
            }, 1000
        )
    }
}