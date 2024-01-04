package com.multiplatform.clubdistances.homeScreen.useCases

import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepository
import javax.inject.Inject

class AddClubUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
) {

    suspend fun invoke(club : Club) {
        clubsRepository.insert(club)
    }
}