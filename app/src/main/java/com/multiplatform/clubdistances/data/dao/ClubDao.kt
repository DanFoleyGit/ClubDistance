package com.multiplatform.clubdistances.data.dao

import androidx.room.*
import com.multiplatform.clubdistances.homeScreen.model.Club
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {

    @Query("SELECT * FROM club_table ORDER BY distance ASC")
    fun getAll(): Flow<List<Club>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Club)

    @Query("DELETE FROM club_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM club_table where club_name = :clubName")
    suspend fun retrieveClubByName(clubName: String): Club
}