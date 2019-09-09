package com.ivan.basketball.mvvm.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ivan.basketball.R
import com.ivan.basketball.mvvm.activities.PlayerViewActivity
import com.ivan.basketball.databinding.RecyclePlayerBinding
import com.ivan.basketball.mvvm.viewmodels.PlayerItemViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class PlayersOverviewRecyclerAdapter(private val teamID: Int, private var players: List<PlayerItemViewModel>) :
    RecyclerView.Adapter<PlayersOverviewRecyclerAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclePlayerBinding = DataBindingUtil.inflate(inflater, R.layout.recycle_player, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playerItemViewModel: PlayerItemViewModel = players[position]
        holder.bind(playerItemViewModel)
    }


    inner class ViewHolder(private val recyclePlayerBinding: RecyclePlayerBinding) :
        RecyclerView.ViewHolder(recyclePlayerBinding.root) {
        fun bind(playerItemViewModel: PlayerItemViewModel) {
            recyclePlayerBinding.playerOverview = playerItemViewModel
            Picasso.get().load(playerItemViewModel.mugshotURL).transform(CropCircleTransformation())
                .into(recyclePlayerBinding.mugshot)
        }

        init {
            recyclePlayerBinding.gameNewsLayout.setOnClickListener {
                val intent = Intent(context, PlayerViewActivity::class.java)
                intent.putExtra("PLAYER_ID", players[position].teamID)
                intent.putExtra("TEAM_ID", teamID)
                context!!.startActivity(intent)
            }
        }
    }
}