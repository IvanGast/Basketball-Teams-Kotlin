package com.ivan.basketball.mvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.basketball.R
import com.ivan.basketball.databinding.ActivityMainBinding
import com.ivan.basketball.mvvm.adapters.TeamsRecyclerAdapter
import com.ivan.basketball.mvvm.viewmodels.lists.TeamItemsListViewModel
import com.ivan.basketball.networking.RetroQuery
import androidx.lifecycle.Observer
import com.ivan.basketball.mvvm.models.BasketballTeam
import com.ivan.basketball.mvvm.viewmodels.TeamItemViewModel
import com.ivan.basketball.networking.Listener

class MainActivity : AppCompatActivity() {

    var teamsRecyclerAdapter: TeamsRecyclerAdapter? = null
    private val teamItemsListViewModel: TeamItemsListViewModel = TeamItemsListViewModel(RetroQuery())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView: RecyclerView = activityMainBinding.mainRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)

        val retroQuery = RetroQuery()
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
                teamsRecyclerAdapter = TeamsRecyclerAdapter(list)
                recyclerView.adapter = teamsRecyclerAdapter
            }

        })
    }
}
