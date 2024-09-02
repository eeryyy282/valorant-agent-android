package com.submission.valorantagentandroid.presentation.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.ui.AgentAdapter
import com.submission.valorantagentandroid.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkDarkMode()
        recyclerViewAgent()
        setupAction()
    }

    private fun setupAction() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.navigation_home, true)
            .build()

        binding.buttonHomeToAgent.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_navigation_agent,
                null,
                navOptions
            )
        }

        binding.buttonShowAllAgentHome.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_navigation_agent,
                null,
                navOptions
            )
        }
    }


    private fun checkDarkMode() {
        val isDarkModeActive =
            (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES)
        with(binding) {
            if (isDarkModeActive) {
                ivLogoHome.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )

                buttonHomeToAgent.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
            } else {
                ivLogoHome.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                buttonHomeToAgent.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
            }
        }
    }

    private fun recyclerViewAgent() {
        if (activity != null) {
            val agentAdapter = AgentAdapter()
            agentAdapter.onItemClick = {

            }

            homeViewModel.agent.observe(viewLifecycleOwner) { agent ->
                if (agent != null) {
                    when (agent) {
                        is Resource.Error -> {
                            binding.progressBarTopAgent.visibility = View.GONE
                        }

                        is Resource.Loading -> binding.progressBarTopAgent.visibility = View.VISIBLE

                        is Resource.Success -> {
                            binding.progressBarTopAgent.visibility = View.GONE
                            agentAdapter.setData(agent.data)
                        }
                    }
                }
            }

            with(binding.rvTopAgentHome) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = agentAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}