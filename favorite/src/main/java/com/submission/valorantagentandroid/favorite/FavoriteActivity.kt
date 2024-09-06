package com.submission.valorantagentandroid.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.submission.valorantagentandroid.core.ui.AgentAdapter
import com.submission.valorantagentandroid.favorite.databinding.ActivityFavoriteBinding
import com.submission.valorantagentandroid.presentation.detail.DetailAgentActivity
import com.submission.valorantagentandroid.presentation.settings.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val settingViewModel: SettingsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadKoinModules(favoriteModule)
        setupAdapter()
        checkDarkMode()

    }

    private fun checkDarkMode() {
        settingViewModel.getThemeSetting.observe(this) { darkMode ->
            if (darkMode) {
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            } else {
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }

    private fun setupAdapter() {
        val agentAdapter = AgentAdapter()
        agentAdapter.onItemClick = { selectedAgent ->
            val intent = Intent(this, DetailAgentActivity::class.java)
            intent.putExtra(DetailAgentActivity.EXTRA_DATA, selectedAgent)
            startActivity(intent)
        }

        favoriteViewModel.favoriteAgent.observe(this) { dataAgent ->
            agentAdapter.setData(dataAgent)
            binding.ivErrorFavoriteAgent.visibility =
                if (dataAgent.isNotEmpty()) View.GONE else View.VISIBLE
            binding.tvErrorFavoriteAgent.visibility =
                if (dataAgent.isNotEmpty()) View.GONE else View.VISIBLE

        }

        with(binding.rvFavoriteAgent) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = agentAdapter
        }
    }
}