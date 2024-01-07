package com.multiplatform.clubdistances.homeScreen.useCases

import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepository
import javax.inject.Inject

class RetrieveClubByNameUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
) {
    suspend fun invoke(clubName : String) : Club {
        return clubsRepository.retrieveClubByName(clubName)
    }
}