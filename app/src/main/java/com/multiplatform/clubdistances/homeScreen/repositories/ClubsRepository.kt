package com.multiplatform.clubdistances.homeScreen.repositories

import androidx.annotation.WorkerThread
import com.multiplatform.clubdistances.homeScreen.dao.ClubDao
import com.multiplatform.clubdistances.homeScreen.model.Club
import kotlinx.coroutines.flow.Flow

class ClubsRepository(private val clubDao: ClubDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allClubs: Flow<List<Club>> = clubDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(club: Club) {
        clubDao.insert(club)
    }
}