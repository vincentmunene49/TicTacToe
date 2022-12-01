package com.example.tictactoe.data

import androidx.annotation.DrawableRes
import com.example.tictactoe.R

class Board(private val gameBoard:MutableMap<Cell,CellState> = mutableMapOf()) {
    fun getBoardState(cell:Cell):CellState{
        return gameBoard[cell] ?: CellState.Empty
    }

    fun setCellState(cell: Cell,cellState: CellState):Boolean{
        if(gameBoard.containsKey(cell)){
            return false
        }
        gameBoard[cell] = cellState
        return true
    }

    //reset game board
    fun resetGameBoard(){
        gameBoard.clear()
    }

    val boardState:gameBoardState
        get() = when{
            //stars win
            stateWon(CellState.star) -> gameBoardState.STARS_WON
            //circles win
            stateWon(CellState.circle) -> gameBoardState.CIRCLES_WON
            //incomplete
            gameBoard.size < 9 -> gameBoardState.INCOMPLETE


            else ->
                gameBoardState.DRAW
        }

    private fun stateWon(state: CellState):Boolean{
        fun containsStateOfInterest(vararg cells:Cell)  = cells.all{
             gameBoard[it] == state
        }

        return  containsStateOfInterest(Cell.TOP_LEFT,Cell.TOP_CENTER, Cell.TOP_RIGHT) ||
                containsStateOfInterest(Cell.CENTER_LEFT,Cell.CENTER, Cell.CENTER_RIGHT) ||
                containsStateOfInterest(Cell.BOTTOM_LEFT,Cell.BOTTOM_CENTER, Cell.BOTTOM_RIGHT) ||
                containsStateOfInterest(Cell.TOP_LEFT,Cell.CENTER_LEFT, Cell.BOTTOM_LEFT) ||
                containsStateOfInterest(Cell.TOP_CENTER,Cell.CENTER, Cell.BOTTOM_CENTER) ||
                containsStateOfInterest(Cell.TOP_RIGHT,Cell.CENTER_RIGHT, Cell.BOTTOM_RIGHT) ||
                containsStateOfInterest(Cell.TOP_LEFT,Cell.CENTER, Cell.BOTTOM_RIGHT) ||
                containsStateOfInterest(Cell.TOP_RIGHT,Cell.TOP_CENTER, Cell.BOTTOM_LEFT)

    }




        }




sealed class CellState(@DrawableRes val drawable:Int){
    object Empty:CellState(R.drawable.ic_blank)
    object star:CellState(R.drawable.ic_star)
    object circle:CellState(R.drawable.ic_circle)

}
enum class Cell{
    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT,
    CENTER_LEFT,
    CENTER,
    CENTER_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT
}