package com.example.mguessinggame2in1
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mguessinggame2in1.RecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class PhraseGame : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        //UI elements
        val showPhrase = findViewById<TextView>(R.id.phrase)
        val showLetter = findViewById<TextView>(R.id.letter)
        val userInput = findViewById<EditText>(R.id.user_input)
        val button = findViewById<Button>(R.id.guess_button)
        val myRV = findViewById<RecyclerView>(R.id.rvMain)
        val myLayout = findViewById<ConstraintLayout>(R.id.screen_layout)
        val rem = findViewById<TextView>(R.id.rem)

        // pre-game
        var remaining = sharedPreferences.getInt("Score", 10)
        val results = mutableListOf<String>()
        val phrases = listOf<String>(
            "A PIECE OF CAKE",
            "NO PAIN NO GAIN",
            "ACTIONS SPEAK LOUDER THAN WORDS",
            "BETTER LATE THAN NEVER",
            "CLOTHES DO NOT MAKE THE MAN",
        )
        val selectedPhrase = phrases[Random.nextInt(phrases.size)]
        var starPhrase = Regex("[A-Za-z]").replace(selectedPhrase, "*")
        showPhrase.text = starPhrase
        var enterPhrase = true

        myRV.adapter = RecyclerViewAdapter(results)
        myRV.layoutManager = LinearLayoutManager(this)


        button.setOnClickListener{
            var input = userInput.text.toString().trim().toUpperCase()
            if(showPhrase.text.contains("*")){
                if(input.isEmpty()){ Snackbar.make(myLayout, "You must enter at least one letter", Snackbar.LENGTH_SHORT).show() }
                else{
                    if(enterPhrase){
                        if(input == selectedPhrase){
                            results.add("Greet job")
                            showPhrase.text = selectedPhrase
                            showPhrase.textSize = 18f
                            button.isClickable = false
                        }
                        else{
                            results.add("Wrong guess: $input")
                        }
                        userInput.setText("")
                        userInput.hint = "Guess a letter"
                        enterPhrase = false
                    }
                    else{
                        showLetter.text = input[0].toString()
                        if(selectedPhrase.contains(input[0])){
                            var counter = 0
                            var phraseChar = showPhrase.text.toString().toCharArray()
                            for(i in 0..selectedPhrase.length-1){
                                if(selectedPhrase[i] == input[0]) {
                                    phraseChar[i] = input[0]
                                    counter++
                                }
                            }
                            showPhrase.text = String(phraseChar)
                            results.add("Found $counter $input(s)")
                        }
                        else{
                            results.add("Wrong guess: $input")
                            results.add("${--remaining} guesses remaining")
                            val editor = sharedPreferences.edit()
                            editor.putInt("Score", remaining)
                            editor.commit()
                            rem.text = sharedPreferences.getInt("Score", remaining).toString()
                        }
                        userInput.setText("")
                        userInput.hint = "Guess the full phrase"
                        enterPhrase = true
                    }
                }
            }
            else{
                results.add("Greet job")
                showPhrase.text = selectedPhrase
                showPhrase.textSize = 18f
                button.isClickable = false
            }

            myRV.adapter = RecyclerViewAdapter(results)
            myRV.layoutManager = LinearLayoutManager(this)
            myRV.scrollToPosition(results.size - 1)

            if(remaining == 0){
                results.add("Game over")
                button.isClickable = false
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.phrase_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.phrase_game -> {
                recreate()
                return true
            }
            R.id.numbers_game -> {
                startActivity(Intent(this, NumbersGame::class.java))
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