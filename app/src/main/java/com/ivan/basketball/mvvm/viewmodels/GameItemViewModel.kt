package com.ivan.basketball.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.ivan.basketball.mvvm.models.BasketballGame

class GameItemViewModel(private val teamID: Int, private val game: BasketballGame) : ViewModel() {

    var selectedTeamName: String? = null
    var opponentTeamName: String? = null
    var isWinner:Boolean? = null
    var date: String? = null

    init{
        setData()
    }

    private fun setData(){
        if (teamID == game.team1ID){
            selectedTeamName = game.team1Name
            opponentTeamName = game.team2Name
            isWinner = (game.team1Score?:0 > game.team2Score?:0)
            date = game.date
        }else{
            selectedTeamName = game.team2Name
            opponentTeamName = game.team1Name
            isWinner = game.team2Score!! > game.team1Score!!
            date = game.date
        }
    }
}