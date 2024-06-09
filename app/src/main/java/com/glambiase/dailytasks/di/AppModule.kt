package com.glambiase.dailytasks.di

import android.content.Context
import androidx.room.Room
import com.glambiase.dailytasks.data.local.DailyTasksDao
import com.glambiase.dailytasks.data.local.DailyTasksDatabase
import com.glambiase.dailytasks.data.repository.DailyTasksRepositoryImpl
import com.glambiase.dailytasks.data.util.Constants.DB_NAME
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDailyTasksDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            DailyTasksDatabase::class.java,
            DB_NAME
        ).build()

    @Singleton
    @Provides
    fun provideDailyTasksDao(database: DailyTasksDatabase) = database.dailyTasksDao()

    @Singleton
    @Provides
    fun provideDailyTasksRepository(dao: DailyTasksDao): DailyTasksRepository = DailyTasksRepositoryImpl(dao)
}