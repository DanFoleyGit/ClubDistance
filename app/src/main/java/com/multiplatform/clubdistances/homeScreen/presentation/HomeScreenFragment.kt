package com.multiplatform.clubdistances.homeScreen.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.multiplatform.clubdistances.databinding.HomeScreenFragmentBinding
import com.multiplatform.clubdistances.homeScreen.adapters.ClubAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment(), ClubAdapter.ClubAdapterCallback {

    private val viewModel: HomeScreenViewModel by viewModels()

    // Binding object instance with access to the views in the game_fragment.xml layout
    private lateinit var binding: HomeScreenFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var clubAdapter: ClubAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        clubAdapter = ClubAdapter()
        recyclerView.adapter = clubAdapter

        bind()
    }

    private fun bind() {
        viewModel.allClubs.observe(viewLifecycleOwner, Observer { clubs ->
            clubs?.let{
                clubAdapter.submitList(clubs)
            }
        })
    }

    override fun onItemClicked(position: Int) {
        TODO("Not yet implemented")
    }

}