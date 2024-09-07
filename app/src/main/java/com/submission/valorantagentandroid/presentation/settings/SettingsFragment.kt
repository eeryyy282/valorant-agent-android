package com.submission.valorantagentandroid.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
