package com.ivan.basketball.mvvm.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ivan.basketball.R
import com.ivan.basketball.mvvm.activities.TeamViewActivity
import com.ivan.basketball.databinding.RecycleTeamBinding
import com.ivan.basketball.mvvm.viewmodels.TeamItemViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class TeamsRecyclerAdapter(var teams: List<TeamItemViewModel>) : RecyclerView.Adapter<TeamsRecyclerAdapter.ViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context!!)
        val binding: RecycleTeamBinding = DataBindingUtil.inflate(inflater, R.layout.recycle_team, parent, false)
        return ViewHolder(context!!, binding)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teamViewModel: TeamItemViewModel = teams[position]
        holder.bind(teamViewModel)
        holder.id = teams[position].id
        holder.title = teams[position].title
        holder.pictureURL = teams[position].iconURL
    }


    inner class ViewHolder(context: Context, private val teamBinding: RecycleTeamBinding, var id: Int? = null, var pictureURL: String? = null, var title: String? = null) : RecyclerView.ViewHolder(teamBinding.root) {
        fun bind(viewItemViewModel: TeamItemViewModel) {
            this.teamBinding.team = viewItemViewModel
            Picasso.get().load(viewItemViewModel.iconURL).transform(CropCircleTransformation()).into(teamBinding.teamIcon)
        }

        init {
            teamBinding.teamCard.setOnClickListener {
                val intent = Intent(context, TeamViewActivity::class.java)
                intent.putExtra(TEAM_ID, id!!)
                intent.putExtra(PICTURE, pictureURL)
                intent.putExtra(TITLE, title)
                context.startActivity(intent)
            }
        }

    }

    companion object{
        const val TEAM_ID = "team_id"
        const val TITLE = "title"
        const val PICTURE = "picture_url"
    }
}