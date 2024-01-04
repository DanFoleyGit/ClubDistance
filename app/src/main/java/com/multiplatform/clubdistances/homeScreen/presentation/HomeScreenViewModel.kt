package com.multiplatform.clubdistances.homeScreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.useCases.GetClubsStaticUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getClubsStaticUseCase: GetClubsStaticUseCase
) : ViewModel() {

    // read club data as live data that is observed by the fragment
    private lateinit var _allClubs: LiveData<List<Club>>
    val allClubs: LiveData<List<Club>>
        get() = _allClubs

    init {
        populateClubsData()
    }

    private fun populateClubsData() {
        _allClubs = getClubsStaticUseCase.invoke().asLiveData()
    }
}
