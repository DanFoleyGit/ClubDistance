package com.multiplatform.clubdistances.di

import com.multiplatform.clubdistances.homeScreen.dao.ClubDao
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepository
import com.multiplatform.clubdistances.homeScreen.repositories.ClubsRepositoryImpl
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
}
