package com.ivan.basketball.mvvm.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.basketball.R
import com.ivan.basketball.databinding.FragmentTeamNewsBinding
import com.ivan.basketball.mvvm.adapters.GameResultsRecyclerAdapter
import com.ivan.basketball.mvvm.adapters.TeamsRecyclerAdapter.Companion.TEAM_ID
import com.ivan.basketball.mvvm.models.BasketballGame
import com.ivan.basketball.mvvm.viewmodels.GameItemViewModel
import com.ivan.basketball.mvvm.viewmodels.lists.GameResultsListViewModel
import com.ivan.basketball.networking.Listener
import com.ivan.basketball.networking.RetroQuery

class TeamNewsFragment : Fragment() {

    private lateinit var fragmentTeamNewsBinding: FragmentTeamNewsBinding

    private lateinit var gameRecyclerView: RecyclerView
    private var gameResultsRecyclerAdapter: GameResultsRecyclerAdapter? = null
    private lateinit var gameResultsListViewModel: GameResultsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentTeamNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_team_news, container, false)

        gameRecyclerView = fragmentTeamNewsBinding.teamNewsRecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)

        val retroQuery = RetroQuery()
        val teamID = arguments?.getInt(TEAM_ID)!!
        retroQuery.pullGames(teamID, object : Listener<List<BasketballGame>> {
            override fun <T> onResult(data: T) {
                val list = ArrayList<GameItemViewModel>()
                for (e in data as List<BasketballGame>){
                    list.add(GameItemViewModel(teamID, e))
                }
                if (gameResultsRecyclerAdapter == null) {
                    gameResultsRecyclerAdapter = GameResultsRecyclerAdapter(list)
                    gameRecyclerView.adapter = gameResultsRecyclerAdapter
                }
                gameRecyclerView.adapter!!.notifyDataSetChanged()
            }
        })

        return fragmentTeamNewsBinding.root
    }

}