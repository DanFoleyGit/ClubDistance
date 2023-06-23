package com.multiplatform.clubdistances.homeScreen.useCases

import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.repositories.GetClubsStaticRepository

class GetClubsStaticUseCase {
    val getClubsStaticRepository : GetClubsStaticRepository = GetClubsStaticRepository()

    fun invoke(): List<Club> {
        return getClubsStaticRepository.getClubList()
    }
}