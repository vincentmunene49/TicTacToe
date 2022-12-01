package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.tictactoe.data.Board
import com.example.tictactoe.data.Cell
import com.example.tictactoe.data.gameBoardState
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.viewmodel.ViewModel
import java.util.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //update or gamestatus and update gameboard

        viewModel.liveBoard.observe(this) {
            updateGameBoard(it)
            updateGameStatus(it.boardState)

        }

        cellClicked()


    }

    private fun cellClicked() = with(binding) {
        cell0.setOnClickListener { viewModel.cellClicked(Cell.TOP_LEFT) }
        cell1.setOnClickListener { viewModel.cellClicked(Cell.TOP_CENTER) }
        cell2.setOnClickListener { viewModel.cellClicked(Cell.TOP_RIGHT) }
        cell3.setOnClickListener { viewModel.cellClicked(Cell.CENTER_LEFT) }
        cell4.setOnClickListener { viewModel.cellClicked(Cell.CENTER) }
        cell5.setOnClickListener { viewModel.cellClicked(Cell.CENTER_RIGHT) }
        cell6.setOnClickListener { viewModel.cellClicked(Cell.BOTTOM_LEFT) }
        cell7.setOnClickListener { viewModel.cellClicked(Cell.BOTTOM_CENTER) }
        cell8.setOnClickListener { viewModel.cellClicked(Cell.BOTTOM_RIGHT) }

        reset.setOnClickListener { viewModel.resetGameBoard() }


    }


    private fun updateGameBoard(board: Board) = with(binding) {
        cell0.setImageResource(board.getBoardState(Cell.TOP_LEFT).drawable)
        cell1.setImageResource(board.getBoardState(Cell.TOP_CENTER).drawable)
        cell2.setImageResource(board.getBoardState(Cell.TOP_RIGHT).drawable)
        cell3.setImageResource(board.getBoardState(Cell.CENTER_LEFT).drawable)
        cell4.setImageResource(board.getBoardState(Cell.CENTER).drawable)
        cell5.setImageResource(board.getBoardState(Cell.CENTER_RIGHT).drawable)
        cell6.setImageResource(board.getBoardState(Cell.BOTTOM_LEFT).drawable)
        cell7.setImageResource(board.getBoardState(Cell.BOTTOM_CENTER).drawable)
        cell8.setImageResource(board.getBoardState(Cell.BOTTOM_RIGHT).drawable)
    }

    //view clicked listener

    private fun updateGameStatus(boardState: gameBoardState) = when (boardState) {
        gameBoardState.STARS_WON -> {
            binding.winner.visibility = View.VISIBLE
            binding.winner.text = "stars won"
        }
        gameBoardState.CIRCLES_WON -> {
            binding.winner.visibility = View.VISIBLE
            binding.winner.text = "circles won"
        }
        gameBoardState.INCOMPLETE -> {
            binding.winner.visibility = View.GONE
        }
        gameBoardState.DRAW -> {
            binding.winner.visibility = View.VISIBLE
            binding.winner.text = "Draw"
        }
    }

}