package com.multiplatform.clubdistances.di

import android.content.Context
import androidx.room.Room
import com.multiplatform.clubdistances.data.ClubRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideClubDao(clubRooDatabase: ClubRoomDatabase)
            = clubRooDatabase.getClubDao()

    @Singleton
    @Provides
    fun provideClubRoomDatabase(@ApplicationContext appContext: Context): ClubRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            ClubRoomDatabase::class.java,
            "RssReader"
        ).build()
    }

}
