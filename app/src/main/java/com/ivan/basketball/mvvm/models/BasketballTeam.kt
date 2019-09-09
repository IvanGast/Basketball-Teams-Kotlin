package com.ivan.basketball.mvvm.models

import com.google.gson.annotations.SerializedName

data class BasketballTeam(
    @SerializedName("idTeam")
    var id: Int,
    @SerializedName("strTeam")
    var title: String?,
    @SerializedName("strDescriptionEN")
    var description: String?,
    @SerializedName("strTeamBadge")
    var iconURL: String?,
    @SerializedName("strTeamFanart1")
    var pictureURL: String?
)