package com.submission.valorantagentandroid.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.ui.ImageProfileAdapter
import com.submission.valorantagentandroid.profile.databinding.ActivityProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val profileViewModel: ProfileViewModel by viewModel()

    private var imageProfile: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadKoinModules(profileModule)
        checkDarkMode()
        setupProfileSaved()
        setupAdapter()
        setupButton()
    }

    private fun checkDarkMode() {
        profileViewModel.getTheme.observe(this) { darkMode ->
            delegate.localNightMode = if (darkMode) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupProfileSaved() {
        profileViewModel.getUsername.observe(this) { username ->
            binding.tieUsernameEdit.setText(
                username.ifEmpty {
                    getString(R.string.guest)
                }
            )
        }
        profileViewModel.getUserBio.observe(this) { userBio ->
            binding.etBioUserEdit.setText(
                userBio.ifEmpty {
                    getString(R.string.my_bio)
                }
            )
        }
        profileViewModel.getUserImage.observe(this) { userImage ->
            imageProfile = userImage.ifEmpty {
                null
            }
        }
    }

    private fun setupAdapter() {
        val imageProfileAdapter = ImageProfileAdapter()
        imageProfileAdapter.onItemClick = { selectedAgent ->
            imageProfile = selectedAgent.displayIcon
        }

        profileViewModel.agent.observe(this) { agent ->
            if (agent != null) {
                with(binding) {
                    when (agent) {
                        is Resource.Error -> {
                            progressBarProfile.visibility = View.GONE
                            with(View.VISIBLE) {
                                ivErrorEditPhotoProfile.visibility = this
                                tvErrorMessageProfile.visibility = this
                            }

                        }

                        is Resource.Loading -> {
                            progressBarProfile.visibility = View.VISIBLE
                            with(View.GONE) {
                                ivErrorEditPhotoProfile.visibility = this
                                tvErrorMessageProfile.visibility = this
                            }
                        }

                        is Resource.Success -> {
                            progressBarProfile.visibility = View.GONE
                            with(View.GONE) {
                                ivErrorEditPhotoProfile.visibility = this
                                tvErrorMessageProfile.visibility = this
                            }

                            val listDataAgent = agent.data
                            imageProfileAdapter.setData(listDataAgent)

                            if (imageProfile != null) {
                                val selectedPosition = listDataAgent?.indexOfFirst {
                                    it.displayIcon == imageProfile
                                } ?: -1
                                imageProfileAdapter.setSelectedPosition(selectedPosition)
                            }
                        }
                    }
                }
            }
        }

        with(binding.rvPhotoProfileEdit) {
            layoutManager = GridLayoutManager(context, 4)
            adapter = imageProfileAdapter
        }
    }

    private fun setupButton() {
        binding.buttonCancelEditProfile.setOnClickListener {
            cancelButton()
        }

        binding.buttonSaveEditProfile.setOnClickListener {
            val username = binding.tieUsernameEdit.text.toString()
            val userBio = binding.etBioUserEdit.text.toString()
            val userImage = imageProfile

            if (userImage != null) {
                saveButton(username = username, userImage = userImage, userBio = userBio)
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.please_choose_your_photo_profile),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun cancelButton() {
        finish()
    }

    private fun saveButton(username: String, userBio: String, userImage: String) {
        val builder = AlertDialog.Builder(this)
            .setMessage(getString(R.string.are_you_sure_you_want_to_save_your_changes))
            .setPositiveButton(getString(R.string.confirm)) { dialog, _ ->
                dialog.dismiss()
                profileViewModel.saveUsername(username)
                profileViewModel.saveUserBio(userBio)
                profileViewModel.saveUserImage(userImage)
                finish()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}
