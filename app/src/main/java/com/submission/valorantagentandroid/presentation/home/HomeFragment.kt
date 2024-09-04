package com.submission.valorantagentandroid.presentation.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.ui.AgentAdapter
import com.submission.valorantagentandroid.core.ui.NewsAdapter
import com.submission.valorantagentandroid.databinding.FragmentHomeBinding
import com.submission.valorantagentandroid.presentation.detail.DetailAgentActivity
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
        recyclerViewNews()
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
            agentAdapter.onItemClick = { selectedAgent ->
                val intent = Intent(activity, DetailAgentActivity::class.java)
                intent.putExtra(DetailAgentActivity.EXTRA_DATA, selectedAgent)
                startActivity(intent)
            }

            homeViewModel.agent.observe(viewLifecycleOwner) { agent ->
                if (agent != null) {
                    with(binding) {
                        when (agent) {
                            is Resource.Error -> {
                                progressBarTopAgent.visibility = View.GONE
                                ivErrorTopAgentHome.visibility = View.VISIBLE
                                tvErrorTopAgentMessageHome.visibility = View.VISIBLE

                            }

                            is Resource.Loading -> {
                                progressBarTopAgent.visibility = View.VISIBLE
                                ivErrorTopAgentHome.visibility = View.GONE
                                tvErrorTopAgentMessageHome.visibility = View.GONE

                            }

                            is Resource.Success -> {
                                progressBarTopAgent.visibility = View.GONE
                                ivErrorTopAgentHome.visibility = View.GONE
                                tvErrorTopAgentMessageHome.visibility = View.GONE
                                agentAdapter.setData(agent.data)
                            }
                        }
                    }
                }

                with(binding.rvTopAgentHome) {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = agentAdapter
                }
            }
        }
    }

    private fun recyclerViewNews() {
        if (activity != null) {
            val newsAdapter = NewsAdapter()
            newsAdapter.onItemClick = { selectedNews ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = selectedNews.url?.toUri()
                startActivity(intent)
            }

            homeViewModel.news.observe(viewLifecycleOwner) { news ->
                if (news != null) {
                    with(binding) {
                        when (news) {
                            is Resource.Error -> {
                                progressBarNews.visibility = View.GONE
                                ivErrorNewsHome.visibility = View.VISIBLE
                                tvErrorNewsmessageHome.visibility = View.VISIBLE

                            }

                            is Resource.Loading -> {
                                progressBarNews.visibility = View.VISIBLE
                                ivErrorNewsHome.visibility = View.GONE
                                tvErrorNewsmessageHome.visibility = View.GONE
                            }

                            is Resource.Success -> {
                                progressBarNews.visibility = View.GONE
                                ivErrorNewsHome.visibility = View.GONE
                                tvErrorNewsmessageHome.visibility = View.GONE
                                newsAdapter.setData(news.data)
                            }
                        }
                    }
                }

                with(binding.rvNewsHome) {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = newsAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}