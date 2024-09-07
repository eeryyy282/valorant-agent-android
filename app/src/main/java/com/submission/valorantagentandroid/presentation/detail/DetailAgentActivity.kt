package com.submission.valorantagentandroid.presentation.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.utils.InsertImageUri.insertGlideImage
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
        checkDarkMode()
    }

    private fun checkDarkMode() {
        detailAgentViewModel.getThemeSetting.observe(this) { darkMode ->
            delegate.localNightMode = if (darkMode) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun setupIntentData() {
        val detailAgent = intent.getParcelableExtra<Agent>(EXTRA_DATA)
        showDetailAgent(detailAgent)
    }

    private fun showDetailAgent(detailAgent: Agent?) {
        detailAgent?.let {
            val gradientDrawable = createGradientDrawable(detailAgent.backgroundGradientColors)
            with(binding) {
                tvAgentNameDetail.text = detailAgent.displayName
                tvAgentDescriptionDetail.text = detailAgent.description
                tvAgentDeveloperNameDetail.text = detailAgent.developerName

                ivBackgroundAgentDetail.insertGlideImage(
                    ivBackgroundAgentDetail.context,
                    detailAgent.background
                )

                ivAgentDetail.insertGlideImage(
                    ivAgentDetail.context,
                    detailAgent.fullPortrait
                )

                ivDisplayIconAgent.insertGlideImage(
                    ivDisplayIconAgent.context,
                    detailAgent.displayIcon
                )


                binding.cvAgentDetailPotrait.background = gradientDrawable

            }
            setupFavoriteAction(detailAgent)
            setupAction(detailAgent)
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

    @SuppressLint("ResourceAsColor")
    private fun setStatusFavorite(statusFavorite: Boolean?) {
        if (statusFavorite == true) {
            binding.buttonFavoriteDetail.let {
                it.text = getString(R.string.remove_favorite)
                it.setBackgroundColor(getColor(R.color.orange))
                it.setCompoundDrawablesWithIntrinsicBounds(R.drawable.remove_icon, 0, 0, 0)
            }
        } else {
            binding.buttonFavoriteDetail.let {
                it.text = getString(R.string.add_favorite)
                it.setBackgroundColor(getColor(R.color.green))
                it.setCompoundDrawablesWithIntrinsicBounds(R.drawable.add_icon, 0, 0, 0)
            }
        }
    }

    private fun setupAction(detailAgent: Agent?) {
        binding.buttonShareDetailAgent.setOnClickListener {
            val agentName = detailAgent?.displayName
            val agentPortrait = detailAgent?.fullPortrait
            val agentDeveloperName = detailAgent?.developerName

            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "I found my favorite agent, $agentName, $agentDeveloperName.\n$agentPortrait"
                )
                type = "text/plain"
            }
            val share = Intent.createChooser(shareIntent, "Share $agentName")
            startActivity(share)

        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
