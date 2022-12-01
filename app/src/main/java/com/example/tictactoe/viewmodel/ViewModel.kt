package com.example.tictactoe.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.data.*

class ViewModel: ViewModel() {
    private var board = Board()
    private var player: String = "player1"
    val liveBoard = MutableLiveData(board)
    private fun updateBoard() {
        liveBoard.value = board
    }


    fun resetGameBoard() {
        board.resetGameBoard()
        updateBoard()
    }

    fun cellClicked(cell: Cell) {
        if (player == "player1") {
            board.setCellState(cell, CellState.star)
            player = "player2"
        } else if (player == "player2") {
            board.setCellState(cell, CellState.circle)
            player = "player1"
        }

        updateBoard()
    }

}