package com.submission.valorantagentandroid.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.databinding.ActivitySplashScreenBinding
import com.submission.valorantagentandroid.presentation.settings.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private val settingViewModel: SettingsViewModel by viewModel()
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkDarkMode()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }

    private fun checkDarkMode() {
        settingViewModel.getThemeSetting.observe(this) { isDarkModeActive ->
            if (isDarkModeActive) {
                binding.ivSplashScreen.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            } else {
                binding.ivSplashScreen.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.black
                    )
                )
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO

            }
        }
    }
}