package com.multiplatform.clubdistances.updateClubs.presentation

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
        initView()
        binding.updateClubsAddClubButton.setOnClickListener {addClub()}
        observeData()
        observeActions()
    }

    private fun initView() {
        binding.updateClubsClubDistanceInput.hint = viewModel.distanceUnits.value
    }

    private fun addClub() {
        viewModel.addClubs(
            binding.updateClubsSelectClubDropDown.text.toString(),
            binding.updateClubsClubLoftInput.text.toString(),
            binding.updateClubsClubBrandInput.text.toString(),
            binding.updateClubsClubDistanceInput.text.toString()
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
                binding.updateClubsSelectClubDropDown.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(userInput: CharSequence?, start: Int, before: Int, count: Int) {
                            viewModel.checkClubExists(userInput.toString())
                        if (userInput != null) {
                            if (userInput.length > 1) {
                                binding.updateClubsSelectClubDropDown.dismissDropDown()
                            }
                        }
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        // Your implementation for beforeTextChanged
                    }

                    override fun afterTextChanged(s: Editable?) {
                        // Your implementation for afterTextChanged
                    }
                })
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
                    Actions.ClearAllInputs -> {
                        binding.updateClubsSelectClubDropDown.setText("")
                        binding.updateClubsClubBrandInput.setText("")
                        binding.updateClubsClubLoftInput.setText("")
                        binding.updateClubsClubDistanceInput.setText("")
                    }
                    Actions.ClearLoftYardageBrandInputs -> {
                        binding.updateClubsClubBrandInput.setText("")
                        binding.updateClubsClubLoftInput.setText("")
                        binding.updateClubsClubDistanceInput.setText("")
                    }
                    is Actions.UpdateFieldsWithValues -> {
                        binding.updateClubsClubBrandInput.setText(it.club.clubBrand)
                        binding.updateClubsClubLoftInput.setText(it.club.clubLoft)
                        binding.updateClubsClubDistanceInput.setText(it.club.distance.toString())
                        context?.let { it1 -> view?.let { it2 -> hideKeyboardFrom(it1, it2) } }

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
