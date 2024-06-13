package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.data.repository.FakeDailyTasksRepository
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.util.EmptyTaskException
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class InsertTaskUseCaseTest {

    private lateinit var insertTaskUseCase: InsertTaskUseCase
    private lateinit var fakeDailyTasksRepository: FakeDailyTasksRepository

    @Before
    fun setUp() {
        fakeDailyTasksRepository = FakeDailyTasksRepository()
        insertTaskUseCase = InsertTaskUseCase(fakeDailyTasksRepository)
    }

    @Test
    fun invoking_with_valid_task_inserts_it() = runTest {
        val task = DailyTask(
            id = 0,
            title = "title",
            description = "description",
            status = TaskStatus.COMPLETATA,
            timestamp = 0L
        )

        insertTaskUseCase(task)

        assertThat(fakeDailyTasksRepository.fetchTasks()).containsExactlyElementsIn(listOf(task))
    }

    @Test
    fun invoking_with_empty_task_throws_EmptyTaskException() = runTest {
        val task = DailyTask(
            id = 0,
            title = "",
            description = "",
            status = TaskStatus.COMPLETATA,
            timestamp = 0L
        )

        assertThrows(EmptyTaskException::class.java) {
            runBlocking {
                insertTaskUseCase(task)
            }
        }
    }

    @Test
    fun throwing_EmptyTaskException_has_correct_message() = runTest {
        val task = DailyTask(
            id = 0,
            title = "",
            description = "",
            status = TaskStatus.COMPLETATA,
            timestamp = 0L
        )

        val exception = assertThrows(EmptyTaskException::class.java) {
            runBlocking {
                insertTaskUseCase(task)
            }
        }
        assertThat(exception).hasMessageThat().contains("Non è possibile salvare un'attività vuota.")
    }
}