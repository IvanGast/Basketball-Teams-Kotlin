package com.ivan.basketball.mvvm.models

import com.google.gson.annotations.SerializedName

data class BasketballPlayer(
    @SerializedName("idPlayer")
    var id: Int,
    @SerializedName("strPlayer")
    var name: String?,
    @SerializedName("dateBorn")
    var birthDate: String?,
    @SerializedName("strHeight")
    var height: String?,
    @SerializedName("strWeight")
    var weight: String?,
    @SerializedName("strDescriptionEN")
    var description: String?,
    @SerializedName("strCutout")
    var mugshotUrl: String?,
    @SerializedName("strThumb")
    var photoURL: String?,
    @SerializedName("idTeam")
    var teamID: Int?,
    @SerializedName("strPosition")
    var position: String?
)