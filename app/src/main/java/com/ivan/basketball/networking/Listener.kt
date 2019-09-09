package com.ivan.basketball.networking

interface Listener <T> {
    fun <T> onResult (data: T)
}