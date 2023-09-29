package com.multiplatform.clubdistances.updateClubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.multiplatform.clubdistances.databinding.FragmentUpdateClubsBinding
import com.multiplatform.clubdistances.updateClubs.composables.OutlinedTextFieldForInputs

class UpdateClubsFragment : Fragment() {

    private var _binding: FragmentUpdateClubsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UpdateClubsViewModel by viewModels()
    private val DISTANCE_UNIT =" Yards"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateClubsBinding.inflate(inflater, container, false)
        val view = binding.root
        observeData()
        return view
    }

    private fun observeData() {
        viewModel.clubNames.observe(viewLifecycleOwner){
            initUI(it)
        }
    }

    private fun initUI(clubNames: List<String>) {
        binding.updateClubsComposeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //create a drop down exposedDropDownMenuBox for club

                            com.multiplatform.clubdistances.updateClubs.composables.ExposedDropdownMenuBox(
                                expanded = false,
                                options = clubNames
                            ) { }
                            Spacer(modifier = Modifier.height(32.dp))
                            OutlinedTextFieldForInputs(inputType = "Loft", hint = "Degrees", suffixText = "°")
                            Spacer(modifier = Modifier.height(32.dp))
                            OutlinedTextFieldForInputs(inputType = "Brand", hint = "", suffixText = "")
                            Spacer(modifier = Modifier.height(32.dp))
                            OutlinedTextFieldForInputs(inputType = "Distance", hint = "Carry", suffixText = DISTANCE_UNIT)
                            Spacer(modifier = Modifier.height(32.dp))

                        }
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