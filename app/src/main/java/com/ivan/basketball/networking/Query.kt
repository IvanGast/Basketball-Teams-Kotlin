package com.ivan.basketball.networking

import com.ivan.basketball.mvvm.models.BasketballGame
import com.ivan.basketball.mvvm.models.BasketballPlayer
import com.ivan.basketball.mvvm.models.BasketballTeam
import com.google.gson.annotations.SerializedName

interface Query {

    fun pullTeams(listId:Int, listener: Listener<List<BasketballTeam>>)
    fun pullGames(listId:Int, listener: Listener<List<BasketballGame>>)
    fun pullPlayers(listId:Int, listener: Listener<List<BasketballPlayer>>)

    data class TeamsJson(
        @SerializedName("teams")
        var teams: List<BasketballTeam>
    )

    data class GamesJson(
        @SerializedName("results")
        var games: List<BasketballGame>
    )

    data class PlayersJson(
        @SerializedName("player")
        var players: List<BasketballPlayer>
    )

}