package com.multiplatform.clubdistances.updateClubs.repositories

import com.multiplatform.clubdistances.homeScreen.model.ClubTypes
import javax.inject.Inject

class ClubTypesRepositoryImpl @Inject constructor(
    private val clubTypes: ClubTypes
) : ClubTypesRepository {
    override fun getClubTypes(): List<String> {
        return clubTypes.getClubTypes()
    }


}