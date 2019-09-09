package com.ivan.basketball.networking

import android.util.Log
import com.ivan.basketball.mvvm.models.BasketballGame
import com.ivan.basketball.mvvm.models.BasketballPlayer
import com.ivan.basketball.mvvm.models.BasketballTeam
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroQuery : Query {
    private val rootURL = "https://www.thesportsdb.com/api/v1/json/1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(rootURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiCall: ApiCall = retrofit.create(ApiCall::class.java)

    override fun pullTeams(listId: Int, listener: Listener<List<BasketballTeam>>) {
        val teamCall: Call<Query.TeamsJson> = apiCall.getTeams(4387)
        if (teamCall.isExecuted) teamCall.clone().enqueue(object : Callback<Query.TeamsJson> {
            override fun onFailure(call: Call<Query.TeamsJson>, t: Throwable) {
                Log.e("Teams Query Call has failed", t.message!!)
            }

            override fun onResponse(call: Call<Query.TeamsJson>, response: Response<Query.TeamsJson>) {
                val data = response.body()
                listener.onResult(data!!.teams)
            }
        })
        else {
            teamCall.enqueue(object : Callback<Query.TeamsJson> {
                override fun onFailure(call: Call<Query.TeamsJson>, t: Throwable) {
                    Log.e("Teams Query Call has failed", t.message!!)
                }

                override fun onResponse(call: Call<Query.TeamsJson>, response: Response<Query.TeamsJson>) {
                    val data = response.body()
                    listener.onResult(data!!.teams)
                }
            })
        }
    }

    override fun pullGames(listId: Int, listener: Listener<List<BasketballGame>>) {
        val eventCall: Call<Query.GamesJson> = apiCall.getEvents(listId)
        if (eventCall.isExecuted) eventCall.clone().enqueue(object : Callback<Query.GamesJson> {
            override fun onFailure(call: Call<Query.GamesJson>, t: Throwable) {
                Log.e("Games Query Call has failed", t.message!!)
            }

            override fun onResponse(call: Call<Query.GamesJson>, response: Response<Query.GamesJson>) {
                val data = response.body()
                listener.onResult(data!!.games)
            }
        })
        else {
            eventCall.enqueue(object : Callback<Query.GamesJson> {
                override fun onFailure(call: Call<Query.GamesJson>, t: Throwable) {
                    Log.e("Games Query Call has failed", t.message!!)
                }

                override fun onResponse(call: Call<Query.GamesJson>, response: Response<Query.GamesJson>) {
                    val data = response.body()
                    listener.onResult(data!!.games)
                }
            })
        }
    }

    override fun pullPlayers(listId: Int, listener: Listener<List<BasketballPlayer>>) {
        val playerCall: Call<Query.PlayersJson> = apiCall.getPlayers(listId)
        if (playerCall.isExecuted) playerCall.clone().enqueue(object : Callback<Query.PlayersJson> {
            override fun onFailure(call: Call<Query.PlayersJson>, t: Throwable) {
                Log.e("PlayersEntity Query Call has failed", t.message!!)
            }

            override fun onResponse(call: Call<Query.PlayersJson>, response: Response<Query.PlayersJson>) {
                val data = response.body()
                listener.onResult(data!!.players)
            }
        })
        else {
            playerCall.enqueue(object : Callback<Query.PlayersJson> {
                override fun onFailure(call: Call<Query.PlayersJson>, t: Throwable) {
                    Log.e("PlayersEntity Query Call has failed", t.message!!)
                }

                override fun onResponse(call: Call<Query.PlayersJson>, response: Response<Query.PlayersJson>) {
                    val data = response.body()
                    listener.onResult(data!!.players)
                }
            })
        }
    }
}