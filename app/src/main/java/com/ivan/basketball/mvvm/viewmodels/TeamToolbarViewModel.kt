package com.ivan.basketball.mvvm.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ivan.basketball.networking.Listener
import com.ivan.basketball.networking.RetroQuery

class TeamToolbarViewModel(private val teamId: Int, var retroQuery: RetroQuery) : ViewModel() {

    var teamNameObservable = ObservableField<String>()

    fun setData(title: String) {
        teamNameObservable.set(title)
    }

    fun getData(listener: Listener<String>){
    }

}