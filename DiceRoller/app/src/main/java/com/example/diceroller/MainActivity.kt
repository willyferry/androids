package com.example.diceroller

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Roll the dice and update the screen with the result.
     * */
    private fun rollDice() {
        // call instance class
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // set it to image view
        val diceImage: ImageView = findViewById(R.id.imageView)
        if(diceRoll == 1) {
            diceImage.setImageResource(R.drawable.dice_1)
        }
        else if(diceRoll == 2) {
            diceImage.setImageResource(R.drawable.dice_2)
        }
        else if (diceRoll == 3) {
            diceImage.setImageResource(R.drawable.dice_3)
        }
        else if(diceRoll == 4) {
            diceImage.setImageResource(R.drawable.dice_4)
        }
        else if (diceRoll == 5) {
            diceImage.setImageResource(R.drawable.dice_5)
        }
        else {
            diceImage.setImageResource(R.drawable.dice_6)
        }
    }
}

class Dice (val diceSides: Int) {
    fun roll(): Int{
        return (1..diceSides).random()
    }
}