package com.ivan.basketball.mvvm.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ivan.basketball.R
import com.ivan.basketball.databinding.ActivityTeamViewBinding
import com.ivan.basketball.mvvm.activities.fragments.TeamNewsFragment
import com.ivan.basketball.mvvm.activities.fragments.TeamPlayersFragment
import com.ivan.basketball.mvvm.adapters.PagerAdapter
import com.ivan.basketball.mvvm.adapters.TeamsRecyclerAdapter.Companion.PICTURE
import com.ivan.basketball.mvvm.adapters.TeamsRecyclerAdapter.Companion.TEAM_ID
import com.ivan.basketball.mvvm.adapters.TeamsRecyclerAdapter.Companion.TITLE
import com.ivan.basketball.mvvm.viewmodels.TeamToolbarViewModel
import com.ivan.basketball.networking.RetroQuery
import com.squareup.picasso.Picasso

class TeamViewActivity : AppCompatActivity() {

    lateinit var mViewPager: ViewPager

    lateinit var activityTeamViewBinding: ActivityTeamViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_view)

        activityTeamViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_team_view)
        val teamToolbarViewModel = TeamToolbarViewModel(intent.getIntExtra(TEAM_ID, 0), RetroQuery())

        teamToolbarViewModel.setData(intent.getStringExtra(TITLE))
        activityTeamViewBinding.team = teamToolbarViewModel

        val pictureURL = intent.getStringExtra(PICTURE)
        Picasso.get().load(pictureURL).into(activityTeamViewBinding.teamPicture)

        mViewPager = activityTeamViewBinding.viewPager
        setupViewPager(mViewPager)

        val tabLayout = activityTeamViewBinding.tabs
        tabLayout.setupWithViewPager(mViewPager)


        setSupportActionBar(activityTeamViewBinding.teamToolbar)
        super.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        super.getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        super.getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_48dp)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val id = intent.getIntExtra(TEAM_ID, -1)

        if (id == -1) {
            Toast.makeText(baseContext, "Error opening", Toast.LENGTH_SHORT).show()
            finish()
        }

        val adapter = PagerAdapter(supportFragmentManager)

        addFragment(id, adapter, TeamNewsFragment(), "News")
        addFragment(id, adapter, TeamPlayersFragment(), "Players")

        viewPager.adapter = adapter
    }

    private fun addFragment(
        id: Int,
        adapter: PagerAdapter,
        fragment: Fragment,
        name: String
    ) {
        val bundle = Bundle()
        bundle.putInt(TEAM_ID, id)
        fragment.arguments = bundle
        adapter.addFragment(fragment, name)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
