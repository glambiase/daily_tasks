package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.data.repository.FakeDailyTasksRepository
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class GetSelectedTaskUseCaseTest {

    private lateinit var getSelectedTaskUseCase: GetSelectedTaskUseCase
    private lateinit var fakeDailyTasksRepository: FakeDailyTasksRepository

    @Before
    fun setUp() {
        fakeDailyTasksRepository = FakeDailyTasksRepository()
        getSelectedTaskUseCase = GetSelectedTaskUseCase(fakeDailyTasksRepository)
    }

    @Test
    fun invoking_with_existing_taskId_returns_task() = runTest {
        val taskId = 0
        val task = DailyTask(
            id = taskId,
            title = "title",
            description = "description",
            status = TaskStatus.DONE,
            timestamp = 0L
        )

        fakeDailyTasksRepository.addTasks(listOf(task))

        val result = getSelectedTaskUseCase(taskId)

        assertThat(result).isEqualTo(task)
    }

    @Test
    fun invoking_with_not_existing_taskId_returns_null() = runTest {
        val taskId = 0
        val task = DailyTask(
            id = 1,
            title = "title",
            description = "description",
            status = TaskStatus.DONE,
            timestamp = 0L
        )

        fakeDailyTasksRepository.addTasks(listOf(task))

        val result = getSelectedTaskUseCase(taskId)

        assertThat(result).isNull()
    }
}