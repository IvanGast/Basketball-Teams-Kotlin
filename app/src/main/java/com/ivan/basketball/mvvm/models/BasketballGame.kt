package com.ivan.basketball.mvvm.models

import com.google.gson.annotations.SerializedName

data class BasketballGame(
    @SerializedName("idEvent")
    var gameID: Int,
    @SerializedName("idHomeTeam")
    var team1ID: Int?,
    @SerializedName("strHomeTeam")
    var team1Name: String?,
    @SerializedName("idAwayTeam")
    var team2ID: Int?,
    @SerializedName("strAwayTeam")
    var team2Name: String?,
    @SerializedName("dateEvent")
    var date: String?,
    @SerializedName("intHomeScore")
    var team1Score: Int?,
    @SerializedName("intAwayScore")
    var team2Score: Int?
)