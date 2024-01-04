package com.multiplatform.clubdistances.updateClubs

import androidx.lifecycle.ViewModel
import com.multiplatform.clubdistances.homeScreen.model.ClubNames
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UpdateClubsViewModel : ViewModel() {

    private var _clubNames = MutableStateFlow(listOf<String>())
    var clubNames = _clubNames.asStateFlow()

    val distanceUnits = MutableStateFlow("Yards")


    init {
        populateClubNamesDropdown()
    }

    private fun populateClubNamesDropdown() {
        _clubNames.value = ClubNames().getClubNames()
    }
}