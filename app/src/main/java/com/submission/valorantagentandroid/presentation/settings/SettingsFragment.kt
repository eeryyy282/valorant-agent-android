package com.submission.valorantagentandroid.presentation.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.core.utils.InsertImageUri.insertGlideImage
import com.submission.valorantagentandroid.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private val settingsViewModel: SettingsViewModel by viewModel()
    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkDarkMode()
        setupSwitchAction()
        setupProfile()
        setupButton()
    }

    private fun checkDarkMode() {
        settingsViewModel.getThemeSetting.observe(viewLifecycleOwner) { isDarkMode ->
            binding.switchDarkMode.isChecked = isDarkMode
        }
    }

    private fun setupSwitchAction() {
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            settingsViewModel.saveThemeSetting(isChecked)
        }
    }

    private fun setupProfile() {
        settingsViewModel.apply {
            getUsername.observe(viewLifecycleOwner) { username ->
                binding.tvUsernameUser.text = username.ifEmpty {
                    getString(R.string.guest)
                }
            }
            getUserBio.observe(viewLifecycleOwner) { userBio ->
                binding.tvBioUser.text = userBio.ifEmpty {
                    getString(R.string.my_bio)
                }
            }
            getUserImage.observe(viewLifecycleOwner) { imageProfile ->
                if (imageProfile.isEmpty()) {
                    settingsViewModel.agent.observe(viewLifecycleOwner) { randomAgent ->
                        if (randomAgent.isNotEmpty()) {
                            val agentDisplayPortrait = randomAgent[0].displayIcon
                            binding.ivProfileUser.insertGlideImage(
                                requireContext(),
                                agentDisplayPortrait
                            )
                            agentDisplayPortrait?.let { settingsViewModel.saveUserImage(it) }
                        } else {
                            Log.w("SettingsFragment", getString(R.string.randomagent_list_is_empty))
                        }
                    }
                } else {
                    binding.ivProfileUser.insertGlideImage(
                        requireContext(),
                        imageProfile
                    )
                }
            }
        }
    }

    private fun setupButton() {
        binding.buttonEditProfile.setOnClickListener {
            val uri = Uri.parse("valorantagentandroid://profile")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
