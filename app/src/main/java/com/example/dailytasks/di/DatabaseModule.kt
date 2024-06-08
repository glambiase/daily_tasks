package com.example.dailytasks.di

import android.content.Context
import androidx.room.Room
import com.example.dailytasks.data.local.DailyTasksDatabase
import com.example.dailytasks.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            DailyTasksDatabase::class.java,
            DB_NAME
        ).build()

    @Singleton
    @Provides
    fun provideDao(database: DailyTasksDatabase) = database.dailyTasksDao()
}