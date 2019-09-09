package com.ivan.basketball.mvvm.viewmodels

import androidx.lifecycle.ViewModel

data class PlayerItemViewModel(
    val fullNameWithPos: String? = null,
    val mugshotURL: String? = null, val teamID: Int,
    val playerId: Int? = null
) : ViewModel()