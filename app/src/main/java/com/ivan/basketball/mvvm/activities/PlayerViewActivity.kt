package com.ivan.basketball.mvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.ivan.basketball.R
import com.ivan.basketball.databinding.ActivityPlayerBinding
import com.ivan.basketball.mvvm.viewmodels.PlayerViewModel
import com.ivan.basketball.networking.Listener
import com.ivan.basketball.networking.RetroQuery
import com.squareup.picasso.Picasso

class PlayerViewActivity : AppCompatActivity() {

    lateinit var activityPlayerBinding: ActivityPlayerBinding

    lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        activityPlayerBinding = DataBindingUtil.setContentView(this, R.layout.activity_player)

        playerViewModel = PlayerViewModel(
            intent.getIntExtra("PLAYER_ID", 0),
            intent.getIntExtra("TEAM_ID", 0),
            RetroQuery()
        )

        playerViewModel.getData(object : Listener<String> {
            override fun <T> onResult(data: T) {
                val pictureURL = data as String
                Picasso.get().load(pictureURL).into(activityPlayerBinding.playerPicture)
            }
        })

        activityPlayerBinding.player = playerViewModel
        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(activityPlayerBinding.playerToolbar)
        super.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        super.getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        super.getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_48dp)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
