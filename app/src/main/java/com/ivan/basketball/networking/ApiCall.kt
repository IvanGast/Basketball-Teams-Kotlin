package com.ivan.basketball.networking

import retrofit2.Call
import retrofit2.http.GET

interface ApiCall {

    @GET("lookup_all_teams.php/")
    fun getTeams(@retrofit2.http.Query("id")id: Int): Call<Query.TeamsJson>

    @GET("eventslast.php")
    fun getEvents(@retrofit2.http.Query("id")id: Int): Call<Query.GamesJson>

    @GET("lookup_all_players.php")
    fun getPlayers(@retrofit2.http.Query("id")id: Int): Call<Query.PlayersJson>

}