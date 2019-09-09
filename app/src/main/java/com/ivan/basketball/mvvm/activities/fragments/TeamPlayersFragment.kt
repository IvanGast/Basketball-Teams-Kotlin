package com.ivan.basketball.mvvm.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.basketball.R
import com.ivan.basketball.databinding.FragmentTeamPlayersBinding
import com.ivan.basketball.mvvm.adapters.PlayersOverviewRecyclerAdapter
import com.ivan.basketball.mvvm.adapters.TeamsRecyclerAdapter.Companion.TEAM_ID
import com.ivan.basketball.mvvm.models.BasketballGame
import com.ivan.basketball.mvvm.models.BasketballPlayer
import com.ivan.basketball.mvvm.viewmodels.PlayerItemViewModel
import com.ivan.basketball.mvvm.viewmodels.lists.PlayerItemListViewModel
import com.ivan.basketball.networking.Listener
import com.ivan.basketball.networking.RetroQuery

class TeamPlayersFragment : Fragment() {

    private lateinit var fragmentTeamPlayersBinding: FragmentTeamPlayersBinding

    private lateinit var playerRecyclerView: RecyclerView
    private var playersOverviewRecyclerAdapter: PlayersOverviewRecyclerAdapter? = null
    private lateinit var playerItemListViewModel: PlayerItemListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTeamPlayersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_team_players, container, false)

        playerRecyclerView = fragmentTeamPlayersBinding.teamPlayersRecyclerView
        playerRecyclerView.layoutManager = LinearLayoutManager(context)


        val retroQuery = RetroQuery()
        val teamID = arguments?.getInt(TEAM_ID)!!

        retroQuery.pullPlayers(teamID, object : Listener<List<BasketballPlayer>> {
            override fun <T> onResult(data: T) {
                val list = ArrayList<PlayerItemViewModel>()
                val players = data as List<BasketballPlayer>
                players.forEach { list.add(PlayerItemViewModel(
                    it.name + ", " + it.position,
                    it.mugshotUrl,
                    it.id,
                    teamID)) }
                if (playersOverviewRecyclerAdapter == null){
                    playersOverviewRecyclerAdapter = PlayersOverviewRecyclerAdapter( teamID, list)
                    playerRecyclerView.adapter = playersOverviewRecyclerAdapter
                }
                playerRecyclerView.adapter!!.notifyDataSetChanged()
            }
        })

        return fragmentTeamPlayersBinding.root
    }
}