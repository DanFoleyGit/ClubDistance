package com.multiplatform.clubdistances.homeScreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.model.ClubNames
import com.multiplatform.clubdistances.homeScreen.useCases.AddClubUseCase
import com.multiplatform.clubdistances.homeScreen.useCases.GetClubsStaticUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val addClubUseCase : AddClubUseCase,
    private val getClubsStaticUseCase: GetClubsStaticUseCase
) : ViewModel() {
    private val _isShowFieldsLayout = MutableLiveData(false)
    val isShowFieldsLayout: LiveData<Boolean>
        get() = _isShowFieldsLayout

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _displayErrorState = MutableLiveData(false)
    val displayErrorState: LiveData<Boolean>
        get() = _displayErrorState

    var clubNames: List<String> = listOf()

    // read club data as live data that is observed by the fragment
    val allClubs: LiveData<List<Club>> = getClubsStaticUseCase.invoke().asLiveData()

    init {
        populateClubNamesDropdown()
    }

    private fun insert(club: Club) = viewModelScope.launch {
        addClubUseCase.invoke(club)
    }

    private fun populateClubNamesDropdown() {
        clubNames = ClubNames().getClubNames()
    }


    fun toggleShowFieldsLayout() {
        _isShowFieldsLayout.value = !_isShowFieldsLayout.value!!
    }

    fun addClubs(name: String, loft: String, brand: String, distance: String) {
        if(name.isEmpty() || loft.isEmpty() || brand.isEmpty() || distance.isEmpty()) {
            _errorMessage.value = "Please enter info into all fields"
            _displayErrorState.value = true
        } else {
            insert(Club(name,loft,brand,distance.toInt()))
            resetFields()
        }
    }

    private fun resetFields() {
        _errorMessage.value = ""
        _displayErrorState.value = false
    }

}
