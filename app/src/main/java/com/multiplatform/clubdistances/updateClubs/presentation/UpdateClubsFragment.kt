package com.multiplatform.clubdistances.updateClubs.presentation

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.multiplatform.clubdistances.databinding.FragmentUpdateClubsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateClubsFragment : Fragment() {

    private var _binding: FragmentUpdateClubsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UpdateClubsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateClubsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateClubsAddClubButton.setOnClickListener {addClub()}
        observeData()
        observeActions()
    }

    private fun addClub() {
        viewModel.addClubs(
            binding.updateClubsSelectClubDropDown.text.toString(),
            binding.updateClubsClubLoftInput.text.toString(),
            binding.updateClubsClubBrandInput.text.toString(),
            binding.updateClubsClubYardageInput.text.toString()
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.clubTypes.collect {clubTypesList ->
                binding.updateClubsSelectClubDropDown.setAdapter(
                    ArrayAdapter(
                    requireContext(),
                    R.layout.simple_dropdown_item_1line, clubTypesList
                    )
                )
                binding.updateClubsSelectClubDropDown.setOnTouchListener { v, event ->
                    binding.updateClubsSelectClubDropDown.showDropDown()
                    false
                }

                // add an on item selected listener to retrieve the data if it exists
//                binding.updateClubsSelectClubDropDown.setOnItemClickListener { adapterView, view, i, l ->
//
//                }
            }
        }
    }

    private fun observeActions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actions.collect {
                when(it) {
                    is Actions.ShowError -> {
                        it.let {
                            binding.updateClubsAddClubErrorMessage.text = it.message
                            binding.updateClubsAddClubErrorMessage.visibility = View.VISIBLE
                        }
                    }
                    Actions.ClearError -> {
                        binding.updateClubsAddClubErrorMessage.text = ""
                        binding.updateClubsAddClubErrorMessage.visibility = View.INVISIBLE
                    }
                    Actions.ClearInputs -> {
                        binding.updateClubsSelectClubDropDown.setText("")
                        binding.updateClubsClubBrandInput.setText("")
                        binding.updateClubsClubLoftInput.setText("")
                        binding.updateClubsClubYardageInput.setText("")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
