package com.example.dwtest.core.common.entity

sealed interface Entity<out T> {
    data class Success<T>(val data: T) : Entity<T>

    data class Error(val exception: Throwable) : Entity<Nothing>
}
