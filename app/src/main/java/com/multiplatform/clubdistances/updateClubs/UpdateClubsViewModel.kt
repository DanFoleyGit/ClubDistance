package com.multiplatform.clubdistances.updateClubs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.multiplatform.clubdistances.homeScreen.model.ClubNames

class UpdateClubsViewModel : ViewModel() {

    private var _clubNames = MutableLiveData(listOf<String>())
    var clubNames: MutableLiveData<List<String>> = _clubNames


    init {
        populateClubNamesDropdown()
    }

    private fun populateClubNamesDropdown() {
        clubNames.value = ClubNames().getClubNames()
    }
}