package com.multiplatform.clubdistances.homeScreen.repositories
import com.multiplatform.clubdistances.homeScreen.model.Club
import kotlinx.coroutines.flow.Flow

interface ClubsRepository {
    fun getAllClubs(): Flow<List<Club>>

    suspend fun insert(club : Club)

    suspend fun retrieveClubByName(clubName : String): Club
}