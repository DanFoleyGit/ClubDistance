package com.multiplatform.clubdistances

import android.app.Application
import com.multiplatform.clubdistances.data.ClubRoomDatabase
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepositoryImpl
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class ClubsApplication: Application() {

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ClubRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ClubsRepositoryImpl(database.getClubDao()) }
}