package com.submission.valorantagentandroid.presentation.agent

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.ui.AgentAdapterComplete
import com.submission.valorantagentandroid.core.utils.BackgroundInsertorGradient.createGradientDrawable
import com.submission.valorantagentandroid.core.utils.InsertImageUri.insertGlideImage
import com.submission.valorantagentandroid.databinding.FragmentAgentBinding
import com.submission.valorantagentandroid.presentation.detail.DetailAgentActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AgentFragment : Fragment() {
    private val agentViewModel: AgentViewModel by viewModel()
    private var _binding: FragmentAgentBinding? = null
    private val binding get() = _binding!!
    private lateinit var agentAdapter: AgentAdapterComplete

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAgentOfDay()
        setupRecyclerViewAgent()
        setupSearchAgent()
    }

    private fun setupAgentOfDay() {
        if (activity != null) {
            agentViewModel.agentRandom.observe(viewLifecycleOwner) { agentOfTheDay ->
                val listData = agentOfTheDay.data
                if (!listData.isNullOrEmpty()) {
                    with(binding) {
                        val randomNumber = listData.indices.random()
                        val agent = listData[randomNumber]
                        tvAgentNameOfTheDayAgent.text = agent.displayName
                        tvAgentDeveloperNameAgent.text = agent.developerName
                        tvAgentDescriptionAgent.text = agent.description

                        ivAgentOfTheDayAgent.insertGlideImage(
                            ivAgentOfTheDayAgent.context,
                            agent.fullPortrait,
                        )

                        ivBackgroundAgentOfTheDay.insertGlideImage(
                            ivBackgroundAgentOfTheDay.context,
                            agent.background
                        )

                        val gradientDrawable =
                            createGradientDrawable(agent.backgroundGradientColors)
                        cvAgentOfTheDayAgent.background = gradientDrawable
                        setupAction(agent)
                    }
                }
            }
        }
    }

    private fun setupAction(agent: Agent) {
        binding.cvAgentOfTheDayAgent.setOnClickListener {
            val intent = Intent(activity, DetailAgentActivity::class.java)
            intent.putExtra(DetailAgentActivity.EXTRA_DATA, agent)
            startActivity(intent)
        }
    }

    private fun setupRecyclerViewAgent() {
        if (activity != null) {
            agentAdapter = AgentAdapterComplete()
            agentAdapter.onItemClick = { selectedAgent ->
                val intent = Intent(activity, DetailAgentActivity::class.java)
                intent.putExtra(DetailAgentActivity.EXTRA_DATA, selectedAgent)
                startActivity(intent)
            }

            binding.rvAgentAgent.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvAgentAgent.adapter = agentAdapter

            agentViewModel.agent.observe(viewLifecycleOwner) { agent ->
                if (agent != null) {
                    with(binding) {
                        when (agent) {
                            is Resource.Error -> {
                                progressBarAgent.visibility = View.GONE
                                tvErrorAgent.visibility = View.VISIBLE
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
            }
        }
    }

    private fun setupSearchAgent() {
        binding.tieSearchAgent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                agentViewModel.searchAgent(query = s.toString())
                    .observe(viewLifecycleOwner) { searchResult ->
                        agentAdapter.setData(searchResult)
                    }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
