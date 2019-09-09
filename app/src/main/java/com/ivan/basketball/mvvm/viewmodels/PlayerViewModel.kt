package com.ivan.basketball.mvvm.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ivan.basketball.mvvm.adapters.PlayersOverviewRecyclerAdapter
import com.ivan.basketball.mvvm.models.BasketballPlayer
import com.ivan.basketball.networking.Listener
import com.ivan.basketball.networking.RetroQuery

class PlayerViewModel(private val playerID: Int, private val teamId: Int, private val retroQuery: RetroQuery) : ViewModel() {

    var playerFullNameObservable = ObservableField<String>()
        private set
    var playerAgeObservable = ObservableField<String>()
        private set
    var playerHeightObservable = ObservableField<String>()
        private set
    var playerWeightObservable = ObservableField<String>()
        private set
    var playerDescriptionObservable = ObservableField<String>()
        private set


    fun getData(listener: Listener<String>) {
        retroQuery.pullPlayers(teamId, object : Listener<List<BasketballPlayer>> {
            override fun <T> onResult(data: T) {
                val players = data as List<BasketballPlayer>
                val player = players.find { it.id == playerID }!!
                println("PLAYER")
                println(player)
                playerFullNameObservable.set(player.name)
                playerAgeObservable.set(player.birthDate)
                playerHeightObservable.set(player.height.toString())
                playerWeightObservable.set(player.weight.toString())
                playerDescriptionObservable.set(player.description)
                listener.onResult(player.photoURL)
            }
        })


    }
//    fun getDataFromRepository(listener: Listener<String>) {
//        playersRepo.getPlayerById(playerID, teamId, object : Listener<BasketballPlayer> {
//            override fun <T> onResult(data: T) {
//                val player = data as BasketballPlayer
//                playerFullNameObservable.set(player.name)
//                playerAgeObservable.set(player.birthDate)
//                playerHeightObservable.set(player.height.toString())
//                playerWeightObservable.set(player.weight.toString())
//                playerDescriptionObservable.set(player.description)
//                listener.onResult(player.photoURL)
//            }
//        })
//    }

}