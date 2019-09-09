package com.ivan.basketball.mvvm.viewmodels.lists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivan.basketball.mvvm.viewmodels.GameItemViewModel
import com.ivan.basketball.networking.RetroQuery

class GameResultsListViewModel(private val teamID: Int, private val retroQuery: RetroQuery) : ViewModel() {

    val mGameItemsList = MutableLiveData<List<GameItemViewModel>>()

}