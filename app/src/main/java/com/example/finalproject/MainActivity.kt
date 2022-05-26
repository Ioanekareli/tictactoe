package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.finalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        const val CROSS = "X"
        const val NOUGHT = "O"
    }

    enum class Turn {
        NOUGHT,
        CROSS
    }


    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var player1Point:Int = 0
    private var player2Point:Int = 0

    var btnList = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBoard()
        onClick()
        newGame()
    }


    private fun initBoard(){
        btnList.add(binding.btn1)
        btnList.add(binding.btn2)
        btnList.add(binding.btn3)
        btnList.add(binding.btn4)
        btnList.add(binding.btn5)
        btnList.add(binding.btn6)
        btnList.add(binding.btn7)
        btnList.add(binding.btn8)
        btnList.add(binding.btn9)
    }


    private fun onClick(){
        for(btn in btnList){
            btn.setOnClickListener {
                addToBoard(btn)

                if (checkForWin(CROSS)){
                    player1Point++
                    binding.teamOneScore.text = player1Point.toString()
                    noClickable()
                    Toast.makeText(this, "Player 1 Won", Toast.LENGTH_SHORT).show()
                }

                if (checkForWin(NOUGHT)){
                    player2Point++
                    binding.teamTwoScore.text = player2Point.toString()
                    noClickable()
                    Toast.makeText(this, "Player 2 Won", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    private fun newGame(){
        binding.resetButton.setOnClickListener {
            resetBoard()
        }
    }

    private fun resetBoard(){
        for(btn in btnList){
            btn.text = ""
            btn.isClickable = true

            if (firstTurn == Turn.NOUGHT)
                firstTurn = Turn.CROSS
            else if (firstTurn == Turn.CROSS)
                firstTurn = Turn.NOUGHT

            currentTurn = firstTurn
            setTurn()
        }


    }

    private fun noClickable(){
        for (btn in btnList){
            btn.isClickable = false
        }

    }


    private fun checkForWin(s:String):Boolean{
//        Horizontal Win
        if (match(binding.btn1,s) && match(binding.btn2,s) && match(binding.btn3,s))
            return true
        if (match(binding.btn4,s) && match(binding.btn5,s) && match(binding.btn6,s))
            return true
        if (match(binding.btn7,s) && match(binding.btn8,s) && match(binding.btn9,s))
            return true
//        Vertical Win
        if (match(binding.btn1,s) && match(binding.btn4,s) && match(binding.btn7,s))
            return true
        if (match(binding.btn2,s) && match(binding.btn5,s) && match(binding.btn8,s))
            return true
        if (match(binding.btn3,s) && match(binding.btn6,s) && match(binding.btn9,s))
            return true
//        Diagonal Win
        if (match(binding.btn1,s) && match(binding.btn5,s) && match(binding.btn9,s))
            return true
        if (match(binding.btn3,s) && match(binding.btn5,s) && match(binding.btn7,s))
            return true

        return false
    }

    private fun match(button: Button, symbol : String): Boolean = button.text == symbol


    private fun setTurn() {
        var turnText = ""
        if (currentTurn == Turn.CROSS)
            turnText = "Player 1 Turn"
        else if (currentTurn == Turn.NOUGHT)
            turnText = "Player 2 Turn"
        binding.playerTurn.text = turnText
    }


    private fun addToBoard(btn:Button){
        if(btn.text != "")
            return

        if(currentTurn == Turn.NOUGHT){
            btn.text = NOUGHT
            currentTurn = Turn.CROSS
        }
        else if (currentTurn == Turn.CROSS){
            btn.text = CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurn()
    }



}