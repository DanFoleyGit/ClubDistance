package com.multiplatform.clubdistances.di

import com.multiplatform.clubdistances.data.dao.ClubDao
import com.multiplatform.clubdistances.homeScreen.model.ClubTypes
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepository
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepositoryImpl
import com.multiplatform.clubdistances.updateClubs.repositories.ClubTypesRepository
import com.multiplatform.clubdistances.updateClubs.repositories.ClubTypesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideClubsRepository(clubDao: ClubDao) : ClubsRepository {
        return ClubsRepositoryImpl(clubDao)
    }

    @Provides
    fun provideClubTypesRepository() : ClubTypesRepository {
        return ClubTypesRepositoryImpl(ClubTypes())
    }
}
