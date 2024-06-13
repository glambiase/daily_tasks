package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.data.repository.FakeDailyTasksRepository
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteAllTasksUseCaseTest {

    private lateinit var deleteAllTasksUseCase: DeleteAllTasksUseCase
    private lateinit var fakeDailyTasksRepository: FakeDailyTasksRepository

    @Before
    fun setUp() {
        fakeDailyTasksRepository = FakeDailyTasksRepository()
        deleteAllTasksUseCase = DeleteAllTasksUseCase(fakeDailyTasksRepository)

        val dailyTasks = mutableListOf<DailyTask>()
        (0..11).forEach {
            dailyTasks.add(
                DailyTask(
                    id = it,
                    title = "title",
                    description = "description",
                    status = TaskStatus.entries[it % TaskStatus.entries.size],
                    timestamp = it.toLong()
                )
            )
        }

        fakeDailyTasksRepository.addTasks(dailyTasks)
    }

    @Test
    fun invoking_removes_all_tasks() = runTest {
        deleteAllTasksUseCase()

        assertThat(fakeDailyTasksRepository.fetchTasks()).isEmpty()
    }
}