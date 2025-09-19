package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.data.repository.FakeDailyTasksRepository
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.util.Sorting
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
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

        fakeDailyTasksRepository.addTasks(dailyTasks)
    }

    @Test
    fun sorting_by_date_without_filter_returns_correct_order() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByDate, statusFilter = null).first()

        for (i in 0 until tasks.lastIndex) {
            assertThat(tasks[i].timestamp).isAtLeast(tasks[i + 1].timestamp)
        }
    }

    @Test
    fun sorting_by_date_with_filter_by_done_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByDate, statusFilter = TaskStatus.DONE).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.DONE.name)
        }
    }

    @Test
    fun sorting_by_date_with_filter_by_in_progress_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByDate, statusFilter = TaskStatus.IN_PROGRESS).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.IN_PROGRESS.name)
        }
    }

    @Test
    fun sorting_by_date_with_filter_by_to_do_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByDate, statusFilter = TaskStatus.TO_DO).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.TO_DO.name)
        }
    }

    @Test
    fun sorting_by_done_status_without_filter_returns_correct_order() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByDoneStatus, statusFilter = null).first()

        for (i in 0 until tasks.lastIndex) {
            assertThat(tasks[i].status.ordinal).isAtMost(tasks[i + 1].status.ordinal)
        }
    }

    @Test
    fun sorting_by_done_status_with_filter_by_done_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByDoneStatus, statusFilter = TaskStatus.DONE).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.DONE.name)
        }
    }

    @Test
    fun sorting_by_done_status_with_filter_by_in_progress_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByDoneStatus, statusFilter = TaskStatus.IN_PROGRESS).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.IN_PROGRESS.name)
        }
    }

    @Test
    fun sorting_by_done_status_with_filter_by_to_do_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByDoneStatus, statusFilter = TaskStatus.TO_DO).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.TO_DO.name)
        }
    }

    @Test
    fun sorting_by_to_do_status_without_filter_returns_correct_order() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByToDoStatus, statusFilter = null).first()

        for (i in 0 until tasks.lastIndex) {
            assertThat(tasks[i].status.ordinal).isAtLeast(tasks[i + 1].status.ordinal)
        }
    }

    @Test
    fun sorting_by_to_do_status_with_filter_by_done_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByToDoStatus, statusFilter = TaskStatus.DONE).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.DONE.name)
        }
    }

    @Test
    fun sorting_by_to_do_status_with_filter_by_in_progress_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByToDoStatus, statusFilter = TaskStatus.IN_PROGRESS).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.IN_PROGRESS.name)
        }
    }

    @Test
    fun sorting_by_to_do_status_with_filter_by_to_do_status_returns_correct_list() = runTest {
        val tasks = getTasksUseCase(sorting = Sorting.ByToDoStatus, statusFilter = TaskStatus.TO_DO).first()

        for (i in 0..tasks.lastIndex) {
            assertThat(tasks[i].status.name).isEqualTo(TaskStatus.TO_DO.name)
        }
    }
}