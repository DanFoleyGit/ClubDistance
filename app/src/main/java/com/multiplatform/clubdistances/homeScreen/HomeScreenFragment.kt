package com.multiplatform.clubdistances.homeScreen

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.multiplatform.clubdistances.ClubsApplication
import com.multiplatform.clubdistances.databinding.HomeScreenFragmentBinding
import com.multiplatform.clubdistances.homeScreen.adapters.ClubAdapter


class HomeScreenFragment : Fragment() {

    private val viewModel: HomeScreenViewModel by viewModels() {
        HomeScreenViewModelFactory((activity?.application as ClubsApplication).repository)
    }
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

        // club names drop down
        binding.selectClubDropDown.setAdapter(ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_dropdown_item_1line, viewModel.clubNames
        ))

        binding.selectClubDropDown.setOnTouchListener { v, event ->
            binding.selectClubDropDown.showDropDown()
            false
        }

        binding.modifyButton.setOnClickListener {toggleLayout()}
        binding.addClubButton.setOnClickListener {addClub()}

        bind()
    }

    private fun bind() {
        viewModel.isShowFieldsLayout.observe(viewLifecycleOwner){
                isShowFieldsLayout -> binding.modifyClubsLayout.isVisible = isShowFieldsLayout
            if (isShowFieldsLayout) {
                binding.modifyButton.text = "Close Menu"
            } else {
                binding.modifyButton.text = "Modify Clubs"
            }
        }

        viewModel.displayErrorState.observe(viewLifecycleOwner) {
                displayErrorState ->
            binding.addClubErrorMessage.text = viewModel.errorMessage.value
            binding.addClubErrorMessage.isVisible = displayErrorState
        }
        viewModel.allClubs.observe(viewLifecycleOwner, Observer { clubs ->
            clubs?.let{
                clubAdapter.submitList(clubs)
            }
        })
    }

    private fun addClub() {
        viewModel.addClubs(
            binding.selectClubDropDown.text.toString(),
            binding.ClubLoftInput.text.toString(),
            binding.ClubBrandInput.text.toString(),
            binding.ClubYardageInput.text.toString()
        )
    }

    private fun toggleLayout() {
        viewModel.toggleShowFieldsLayout()
    }

}