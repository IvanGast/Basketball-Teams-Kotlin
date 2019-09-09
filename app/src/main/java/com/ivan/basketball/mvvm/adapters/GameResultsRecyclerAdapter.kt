package com.ivan.basketball.mvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ivan.basketball.R
import com.ivan.basketball.databinding.RecycleGameBinding
import com.ivan.basketball.mvvm.viewmodels.GameItemViewModel

class GameResultsRecyclerAdapter(private var games: List<GameItemViewModel>) :
    RecyclerView.Adapter<GameResultsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecycleGameBinding = DataBindingUtil.inflate(inflater, R.layout.recycle_game, parent, false)
        return ViewHolder(parent.context, binding)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teamGameViewModel: GameItemViewModel = games[position]
        holder.bind(teamGameViewModel, games[position].isWinner!!)
    }


    inner class ViewHolder(private val context: Context, private val recycleGameBinding: RecycleGameBinding) :
        RecyclerView.ViewHolder(recycleGameBinding.root) {
        fun bind(teamGameModel: GameItemViewModel, won: Boolean) {

            if(won) recycleGameBinding.root.setBackgroundColor(context.resources.getColor(R.color.won_game))
            else recycleGameBinding.root.setBackgroundColor(context.resources.getColor(R.color.lost_game))

            this.recycleGameBinding.game = teamGameModel
        }
    }
}