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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.ui.AgentAdapter
import com.submission.valorantagentandroid.core.ui.NewsAdapter
import com.submission.valorantagentandroid.core.utils.BackgroundInsertorGradient
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
            } else {
                ivLogoHome.setColorFilter(
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
                            is com.submission.valorantagentandroid.core.data.Resource.Error -> {
                                progressBarTopAgent.visibility = View.GONE
                                ivErrorTopAgentHome.visibility = View.VISIBLE
                                tvErrorTopAgentMessageHome.visibility = View.VISIBLE

                            }

                            is com.submission.valorantagentandroid.core.data.Resource.Loading -> {
                                progressBarTopAgent.visibility = View.VISIBLE
                                ivErrorTopAgentHome.visibility = View.GONE
                                tvErrorTopAgentMessageHome.visibility = View.GONE

                            }

                            is com.submission.valorantagentandroid.core.data.Resource.Success -> {
                                progressBarTopAgent.visibility = View.GONE
                                ivErrorTopAgentHome.visibility = View.GONE
                                tvErrorTopAgentMessageHome.visibility = View.GONE

                                val listDataAgent = agent.data
                                agentAdapter.setData(listDataAgent)

                                if (listDataAgent != null) {
                                    Glide.with(this@HomeFragment)
                                        .load(listDataAgent[2].fullPortrait)
                                        .into(ivAgentHome)
                                    Glide.with(this@HomeFragment)
                                        .load(listDataAgent[2].background)
                                        .into(ivAgentBackgroundHome)
                                    val colorBackgroundGradient =
                                        BackgroundInsertorGradient.createGradientDrawable(
                                            listDataAgent[2].backgroundGradientColors
                                        )
                                    cvHome.background = colorBackgroundGradient
                                }
                                setupAction(listDataAgent?.get(2))
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
                            is com.submission.valorantagentandroid.core.data.Resource.Error -> {
                                progressBarNews.visibility = View.GONE
                                ivErrorNewsHome.visibility = View.VISIBLE
                                tvErrorNewsmessageHome.visibility = View.VISIBLE

                            }

                            is com.submission.valorantagentandroid.core.data.Resource.Loading -> {
                                progressBarNews.visibility = View.VISIBLE
                                ivErrorNewsHome.visibility = View.GONE
                                tvErrorNewsmessageHome.visibility = View.GONE
                            }

                            is com.submission.valorantagentandroid.core.data.Resource.Success -> {
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

    private fun setupAction(agent: Agent?) {
        binding.ivAgentHome.setOnClickListener {
            val intent = Intent(activity, DetailAgentActivity::class.java)
            intent.putExtra(DetailAgentActivity.EXTRA_DATA, agent)
            startActivity(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}