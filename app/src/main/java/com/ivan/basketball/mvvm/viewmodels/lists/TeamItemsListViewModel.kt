package com.ivan.basketball.mvvm.viewmodels.lists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivan.basketball.mvvm.models.BasketballTeam
import com.ivan.basketball.mvvm.viewmodels.TeamItemViewModel
import com.ivan.basketball.networking.Listener
import com.ivan.basketball.networking.RetroQuery

class TeamItemsListViewModel(var retroQuery: RetroQuery) : ViewModel() {

    val mTeamItemsList = MutableLiveData <List<TeamItemViewModel>>()

    fun getData() {
        retroQuery.pullTeams(4387, object : Listener<List<BasketballTeam>> {
            override fun <T> onResult(data: T) {
                val repo = data as ArrayList<BasketballTeam>
                val list = ArrayList<TeamItemViewModel>()
                for (e in repo) {
                    list.add(
                        TeamItemViewModel(
                            e.id,
                            e.title?:"",
                            e.description?:"",
                            e.iconURL?:""
                        )
                    )
                }
                mTeamItemsList.value = list
            }
        })
    }
}