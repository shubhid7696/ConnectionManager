package com.example.connectionmanager.connectivityObserver

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observeNetwork(): Flow<Status>

    enum class Status {
        Availiable, Unavailiable, Looseing, Lost
    }
}