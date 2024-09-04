package com.submission.valorantagentandroid.presentation.agent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.ui.AgentAdapterComplete
import com.submission.valorantagentandroid.core.utils.BackgroundInsertorGradient.createGradientDrawable
import com.submission.valorantagentandroid.databinding.FragmentAgentBinding
import com.submission.valorantagentandroid.presentation.detail.DetailAgentActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AgentFragment : Fragment() {
    private val agentViewModel: AgentViewModel by viewModel()
    private var _binding: FragmentAgentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAgentOfDay()
        recyclerViewAgent()
    }


    private fun setupAgentOfDay() {
        if (activity != null) {
            val randomNumber = (1..10).random()
            agentViewModel.agentRandom.observe(viewLifecycleOwner) { agentOfTheDay ->
                val listData = agentOfTheDay.data
                if (listData != null) {
                    binding.tvAgentNameOfTheDayAgent.text = listData[randomNumber].displayName
                    binding.tvAgentDeveloperNameAgent.text = listData[randomNumber].developerName
                    binding.tvAgentDescriptionAgent.text = listData[randomNumber].description
                    Glide.with(this)
                        .load(listData[randomNumber].background)
                        .into(binding.ivBackgroundAgentOfTheDay)
                    Glide.with(this)
                        .load(listData[randomNumber].fullPortrait)
                        .into(binding.ivAgentOfTheDayAgent)
                    val gradientDrawable =
                        createGradientDrawable(listData[randomNumber].backgroundGradientColors)
                    binding.cvAgentOfTheDayAgent.background = gradientDrawable

                }
                setupAction(listData?.get(randomNumber))
            }
        }
    }

    private fun setupAction(agent: Agent?) {
        binding.cvAgentOfTheDayAgent.setOnClickListener {
            val intent = Intent(activity, DetailAgentActivity::class.java)
            intent.putExtra(DetailAgentActivity.EXTRA_DATA, agent)
            startActivity(intent)
        }
    }

    private fun recyclerViewAgent() {
        if (activity != null) {
            val agentAdapter = AgentAdapterComplete()
            agentAdapter.onItemClick = { selectedAgent ->
                val intent = Intent(activity, DetailAgentActivity::class.java)
                intent.putExtra(DetailAgentActivity.EXTRA_DATA, selectedAgent)
                startActivity(intent)
            }

            agentViewModel.agent.observe(viewLifecycleOwner) { agent ->
                if (agent != null) {
                    with(binding) {
                        when (agent) {
                            is Resource.Error -> {
                                progressBarAgent.visibility = View.GONE
                                tvErrorAgent.visibility = View.GONE
                                ivErrorAgent.visibility = View.VISIBLE
                            }

                            is Resource.Loading -> {
                                progressBarAgent.visibility = View.VISIBLE
                                tvErrorAgent.visibility = View.GONE
                                ivErrorAgent.visibility = View.GONE
                            }

                            is Resource.Success -> {
                                progressBarAgent.visibility = View.GONE
                                tvErrorAgent.visibility = View.GONE
                                ivErrorAgent.visibility = View.GONE
                                agentAdapter.setData(agent.data)

                            }
                        }
                    }
                }
                with(binding.rvAgentAgent) {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = agentAdapter
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}