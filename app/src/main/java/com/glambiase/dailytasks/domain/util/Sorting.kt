package com.glambiase.dailytasks.domain.util

sealed class Sorting {

    object ByToDoStatus : Sorting()

    object ByDoneStatus : Sorting()

    object ByDate : Sorting()
}