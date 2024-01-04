package com.multiplatform.clubdistances.updateClubs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.useCases.AddClubUseCase
import com.multiplatform.clubdistances.updateClubs.useCases.GetClubTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateClubsViewModel @Inject constructor(
    private val getClubTypesUseCase: GetClubTypesUseCase,
    private val addClubUseCase: AddClubUseCase
): ViewModel() {

    private val _actions = MutableSharedFlow<Actions>()
    val actions = _actions.asSharedFlow()

    private var _clubTypes = MutableStateFlow(listOf<String>())
    var clubTypes = _clubTypes.asStateFlow()

    val distanceUnits = MutableStateFlow("Yards")

//    fetch club types
    init {
        populateClubTypesDropdown()
    }

    private fun populateClubTypesDropdown() {
        _clubTypes.value = getClubTypesUseCase.invoke()
    }

    //insert new club/ update existing
    fun addClubs(name: String, loft: String, brand: String, distance: String) {
        viewModelScope.launch {
            if(name.isEmpty() || loft.isEmpty() || brand.isEmpty() || distance.isEmpty()) {
                _actions.emit(Actions.ShowError("Please enter info into all fields"))
            } else {
                insert(Club(name,loft,brand,distance.toInt()))
                _actions.emit(Actions.ClearError)
                _actions.emit(Actions.ClearInputs)
                // should give user feedback with Toast
                // need to get a response from database if its been used before
            }
        }
    }

    private fun insert(club: Club) = viewModelScope.launch {
        addClubUseCase.invoke(club)
    }
}

sealed class Actions {
    data class ShowError(val message: String) : Actions()
    object ClearError : Actions()
    object ClearInputs : Actions()
}
