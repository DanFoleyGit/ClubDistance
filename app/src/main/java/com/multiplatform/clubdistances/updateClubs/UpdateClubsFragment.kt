package com.multiplatform.clubdistances.updateClubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.multiplatform.clubdistances.databinding.FragmentUpdateClubsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        //TODO
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}