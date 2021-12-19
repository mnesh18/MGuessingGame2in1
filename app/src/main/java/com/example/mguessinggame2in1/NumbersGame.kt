package com.example.mguessinggame2in1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import kotlin.random.Random

class NumbersGame : AppCompatActivity() {

    private fun dialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Play again?")
        builder.setPositiveButton("Yes") { _: DialogInterface, i: Int -> this.recreate()}
        builder.setNegativeButton("No") { _: DialogInterface, i: Int -> finish()}
        builder.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val myLayout = findViewById<ConstraintLayout>(R.id.clMain)
        val button = findViewById<Button>(R.id.guess_button)
        val number = findViewById<EditText>(R.id.guess_number) // user input
        val guessesCounter = findViewById<TextView>(R.id.guesses_left)
        val guessInput = findViewById<TextView>(R.id.user_input)
        val great = findViewById<TextView>(R.id.great)
        var counter = 3

        var randomNumber = Random.nextInt(10)

        button.setOnClickListener{
            try {
                val num = number.text.toString()!!.toInt()
                if(num != randomNumber){
                    guessInput.text = num.toString()
                    guessesCounter.text = (--counter).toString()
                }
                else {
                    great.text = "Great job. The answer is $randomNumber"
                    dialog()
                }
                if (counter == 0){
                    button.isClickable = false
                    dialog()
                }
            }catch (e: Exception){
                Snackbar.make(myLayout, "Only number accepted", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.numbers_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.phrase_game -> {
                startActivity(Intent(this, PhraseGame::class.java))
                return true
            }
            R.id.numbers_game -> {
                recreate()
                return true
            }
            R.id.main_screen -> {
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}