package com.submission.valorantagentandroid.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.submission.valorantagentandroid.core.ui.AgentAdapter
import com.submission.valorantagentandroid.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        if (activity != null) {
            val agentAdapter = AgentAdapter()
            agentAdapter.onItemClick = {

            }

            favoriteViewModel.favoriteAgent.observe(viewLifecycleOwner) { dataAgent ->
                agentAdapter.setData(dataAgent)
                binding.ivErrorFavoriteAgent.visibility =
                    if (dataAgent.isNotEmpty()) View.GONE else View.VISIBLE
                binding.tvErrorFavoriteAgent.visibility =
                    if (dataAgent.isNotEmpty()) View.GONE else View.VISIBLE

            }

            with(binding.rvFavoriteAgent) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = agentAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}