package com.multiplatform.clubdistances.homeScreen.useCases

import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClubsStaticUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
){

    fun invoke(): Flow<List<Club>> {
        return clubsRepository.getAllClubs()
    }
}