package com.ivan.basketball.mvvm.viewmodels

import androidx.lifecycle.ViewModel

data class TeamItemViewModel(
    var id: Int,
    var title: String,
    var description: String,
    var iconURL: String
) : ViewModel()