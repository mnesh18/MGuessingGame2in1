package com.example.mguessinggame2in1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit var clMain: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phraseButton = findViewById<Button>(R.id.phrase)
        val numbersButton = findViewById<Button>(R.id.numbers)
        clMain = findViewById<ConstraintLayout>(R.id.clMain)


        phraseButton.setOnClickListener{
            startActivity(Intent(this, PhraseGame::class.java))
        }

        numbersButton.setOnClickListener{
            startActivity(Intent(this, NumbersGame::class.java))
        }

    }
}