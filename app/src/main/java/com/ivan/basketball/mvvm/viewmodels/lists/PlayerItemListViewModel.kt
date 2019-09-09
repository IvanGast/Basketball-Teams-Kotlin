package com.ivan.basketball.mvvm.viewmodels.lists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivan.basketball.mvvm.viewmodels.PlayerItemViewModel
import com.ivan.basketball.networking.RetroQuery

class PlayerItemListViewModel(private val teamID: Int, private val retroQuery: RetroQuery) : ViewModel() {
    val mPlayerItemsList = MutableLiveData<List<PlayerItemViewModel>>()

}