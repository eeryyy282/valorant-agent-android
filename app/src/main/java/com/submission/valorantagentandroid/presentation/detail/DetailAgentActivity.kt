package com.submission.valorantagentandroid.presentation.detail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.databinding.ActivityDetailAgentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailAgentActivity : AppCompatActivity() {

    private val detailAgentViewModel: DetailAgentViewModel by viewModel()
    private lateinit var binding: ActivityDetailAgentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailAgentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupIntentData()
    }

    @Suppress("DEPRECATION")
    private fun setupIntentData() {
        val detailAgent = intent.getParcelableExtra<Agent>(EXTRA_DATA)
        showDetailAgent(detailAgent)
    }

    private fun showDetailAgent(detailAgent: Agent?) {
        detailAgent?.let {
            binding.tvAgentNameDetail.text = detailAgent.displayName
            binding.tvAgentDescriptionDetail.text = detailAgent.description
            binding.tvAgentDeveloperNameDetail.text = detailAgent.developerName
            Glide.with(this@DetailAgentActivity)
                .load(detailAgent.background)
                .into(binding.ivBackgroundAgentDetail)
            Glide.with(this@DetailAgentActivity)
                .load(detailAgent.fullPortrait)
                .into(binding.ivAgentDetail)
            setupFavoriteAction(detailAgent)
            val gradientDrawable = createGradientDrawable(detailAgent.backgroundGradientColors)
            binding.cvAgentDetailPotrait.background = gradientDrawable
        }
    }

    private fun createGradientDrawable(colorStrings: List<String?>?): GradientDrawable {
        val colors = colorStrings?.mapNotNull { colorString ->
            try {
                colorString?.let {
                    val formattedColorString = if (!it.startsWith("#")) "#$it" else it
                    Color.parseColor(formattedColorString)
                }
            } catch (e: IllegalArgumentException) {
                Log.e(
                    "AgentAdapter",
                    "Invalid color string: $colorString"
                )
                null
            }
        }?.toIntArray() ?: intArrayOf()

        val validColors = if (colors.isNotEmpty()) colors else intArrayOf(Color.TRANSPARENT)

        val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.BL_TR, validColors)

        return gradientDrawable
    }

    private fun setupFavoriteAction(detailAgent: Agent?) {
        detailAgent?.let {
            var statusFavorite = detailAgent.isFavorite
            setStatusFavorite(statusFavorite)
            binding.buttonFavoriteDetail.setOnClickListener {
                statusFavorite = !statusFavorite
                detailAgentViewModel.setFavoriteAgent(detailAgent, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean?) {
        if (statusFavorite == true) {
            binding.buttonFavoriteDetail.let {
                it.text = getString(R.string.remove_favorite)
                it.setBackgroundColor(R.color.orange)
                it.setCompoundDrawablesWithIntrinsicBounds(R.drawable.remove_icon, 0, 0, 0)
            }
        } else {
            binding.buttonFavoriteDetail.let {
                it.text = getString(R.string.add_favorite)
                it.setBackgroundColor(R.color.green)
                it.setCompoundDrawablesWithIntrinsicBounds(R.drawable.add_icon, 0, 0, 0)
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}