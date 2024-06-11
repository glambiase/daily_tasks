package com.glambiase.dailytasks.domain.util

sealed class Sorting {

    data object ByToDoStatus : Sorting()

    data object ByDoneStatus : Sorting()

    data object ByDate : Sorting()
}