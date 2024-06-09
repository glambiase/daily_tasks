package com.glambiase.dailytasks.presentation.util

enum class Event {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}

fun String?.toAction(): Event =
    if (isNullOrBlank()) Event.NO_ACTION else Event.valueOf(this)