package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.data.repository.FakeDailyTasksRepository
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.util.Sorting
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTasksUseCaseTest {

    private lateinit var getTasksUseCase: GetTasksUseCase
    private lateinit var fakeDailyTasksRepository: FakeDailyTasksRepository

    @Before
    fun setUp() {
        fakeDailyTasksRepository = FakeDailyTasksRepository()
        getTasksUseCase = GetTasksUseCase(fakeDailyTasksRepository)

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
        dailyTasks.shuffle()

        runBlocking {
            dailyTasks.forEach {
                fakeDailyTasksRepository.insertTask(it)
            }
        }
    }

    @Test
    fun sorting_by_date_without_filter_returns_correct_order() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByDate, statusFilter = null).first()

        for (i in 0 until tasks.lastIndex) {
            assertThat(tasks[i].timestamp).isAtLeast(tasks[i + 1].timestamp)
        }
    }

    @Test
    fun sorting_by_date_with_filter_by_done_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByDate, statusFilter = TaskStatus.COMPLETATA).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.COMPLETATA.name)
        }
    }

    @Test
    fun sorting_by_date_with_filter_by_in_progress_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByDate, statusFilter = TaskStatus.IN_CORSO).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.IN_CORSO.name)
        }
    }

    @Test
    fun sorting_by_date_with_filter_by_to_do_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByDate, statusFilter = TaskStatus.DA_FARE).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.DA_FARE.name)
        }
    }

    @Test
    fun sorting_by_done_status_without_filter_returns_correct_order() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByDoneStatus, statusFilter = null).first()

        for (i in 0 until tasks.lastIndex) {
            assertThat(tasks[i].status.ordinal).isAtMost(tasks[i + 1].status.ordinal)
        }
    }

    @Test
    fun sorting_by_done_status_with_filter_by_done_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByDoneStatus, statusFilter = TaskStatus.COMPLETATA).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.COMPLETATA.name)
        }
    }

    @Test
    fun sorting_by_done_status_with_filter_by_in_progress_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByDoneStatus, statusFilter = TaskStatus.IN_CORSO).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.IN_CORSO.name)
        }
    }

    @Test
    fun sorting_by_done_status_with_filter_by_to_do_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByDoneStatus, statusFilter = TaskStatus.DA_FARE).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.DA_FARE.name)
        }
    }

    @Test
    fun sorting_by_to_do_status_without_filter_returns_correct_order() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByToDoStatus, statusFilter = null).first()

        for (i in 0 until tasks.lastIndex) {
            assertThat(tasks[i].status.ordinal).isAtLeast(tasks[i + 1].status.ordinal)
        }
    }

    @Test
    fun sorting_by_to_do_status_with_filter_by_done_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByToDoStatus, statusFilter = TaskStatus.COMPLETATA).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.COMPLETATA.name)
        }
    }

    @Test
    fun sorting_by_to_do_status_with_filter_by_in_progress_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByToDoStatus, statusFilter = TaskStatus.IN_CORSO).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.IN_CORSO.name)
        }
    }

    @Test
    fun sorting_by_to_do_status_with_filter_by_to_do_status_returns_correct_list() = runBlocking {
        val tasks = getTasksUseCase(sorting = Sorting.ByToDoStatus, statusFilter = TaskStatus.DA_FARE).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.DA_FARE.name)
        }
    }
}