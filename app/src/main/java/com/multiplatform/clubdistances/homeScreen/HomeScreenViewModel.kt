package com.multiplatform.clubdistances.homeScreen

import androidx.lifecycle.*
import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.model.ClubNames
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepository
import com.multiplatform.clubdistances.homeScreen.repositories.GetClubsStaticRepository
import com.multiplatform.clubdistances.homeScreen.useCases.AddClubUseCase
import com.multiplatform.clubdistances.homeScreen.useCases.GetClubsStaticUseCase
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: ClubsRepository) : ViewModel() {
    // TODO
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
    val getClubsStaticRepository : GetClubsStaticRepository = GetClubsStaticRepository()

    // read club data as live data that is observed by the fragment
    val allClubs: LiveData<List<Club>> = repository.allClubs.asLiveData()

    init {
        populateClubNamesDropdown()
    }

    private fun insert(club: Club) = viewModelScope.launch {
        repository.insert(club)
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

class HomeScreenViewModelFactory(private val repository: ClubsRepository ) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}