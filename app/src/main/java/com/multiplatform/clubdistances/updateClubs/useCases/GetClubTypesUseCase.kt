package com.multiplatform.clubdistances.updateClubs.useCases

import com.multiplatform.clubdistances.updateClubs.repositories.ClubTypesRepository
import javax.inject.Inject

class GetClubTypesUseCase @Inject constructor(
    private val clubTypesRepository: ClubTypesRepository
) {
    fun invoke() : List<String> {
        return clubTypesRepository.getClubTypes()
    }
}