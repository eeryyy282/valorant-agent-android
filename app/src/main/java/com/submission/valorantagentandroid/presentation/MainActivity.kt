package com.submission.valorantagentandroid.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.databinding.ActivityMainBinding
import com.submission.valorantagentandroid.presentation.settings.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val settingViewModel: SettingsViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        checkDarkMode()
        setupAction()
    }

    private fun checkDarkMode() {
        settingViewModel.getThemeSetting.observe(this) { darkMode ->
            delegate.localNightMode = if (darkMode) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }

    private fun setupAction() {
        binding.fabFavorite.setOnClickListener {
            val uri = Uri.parse("valorantagentandroid://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        @Suppress("DEPRECATION")
        binding.navView.setOnNavigationItemSelectedListener(null)
        binding.fabFavorite.setOnClickListener(null)
    }
}
