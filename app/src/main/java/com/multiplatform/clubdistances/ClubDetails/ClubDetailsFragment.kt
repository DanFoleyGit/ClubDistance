package com.multiplatform.clubdistances.ClubDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.multiplatform.clubdistances.R

class ClubDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ClubDetailsFragment()
    }

    private lateinit var viewModel: ClubDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_club_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ClubDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}