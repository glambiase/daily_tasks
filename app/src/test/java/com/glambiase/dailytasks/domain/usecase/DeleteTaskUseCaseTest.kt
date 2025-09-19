package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.data.repository.FakeDailyTasksRepository
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteTaskUseCaseTest {

    private lateinit var deleteTaskUseCase: DeleteTaskUseCase
    private lateinit var fakeDailyTasksRepository: FakeDailyTasksRepository

    @Before
    fun setUp() {
        fakeDailyTasksRepository = FakeDailyTasksRepository()
        deleteTaskUseCase = DeleteTaskUseCase(fakeDailyTasksRepository)
    }

    @Test
    fun invoking_removes_specified_task() = runTest {
        val taskToDelete = DailyTask(
            id = 0,
            title = "title",
            description = "description",
            status = TaskStatus.DONE,
            timestamp = 0L
        )
        val otherTask = DailyTask(
            id = 1,
            title = "title",
            description = "description",
            status = TaskStatus.DONE,
            timestamp = 1L
        )

        fakeDailyTasksRepository.addTasks(listOf(taskToDelete, otherTask))

        deleteTaskUseCase(taskToDelete)

        assertThat(fakeDailyTasksRepository.fetchTasks()).doesNotContain(taskToDelete)
    }

    @Test
    fun invoking_does_not_remove_other_tasks() = runTest {
        val taskToDelete = DailyTask(
            id = 0,
            title = "title",
            description = "description",
            status = TaskStatus.DONE,
            timestamp = 0L
        )
        val otherTask = DailyTask(
            id = 1,
            title = "title",
            description = "description",
            status = TaskStatus.DONE,
            timestamp = 1L
        )

        fakeDailyTasksRepository.addTasks(listOf(taskToDelete, otherTask))

        deleteTaskUseCase(taskToDelete)

        assertThat(fakeDailyTasksRepository.fetchTasks()).containsExactlyElementsIn(listOf(otherTask))
    }
}