package com.bignerdranch.myapplication
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        // сообщение о верном ответе
        trueButton.setOnClickListener {
            val toast = Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            )

            toast.setGravity(Gravity.TOP or Gravity.LEFT, 0, 0)
            toast.show()
        }

        // сообщение о неверном ответе
        falseButton.setOnClickListener {
            Toast.makeText(
                this,
                R.string.wrong_toast,
                Toast.LENGTH_SHORT
            ).show()

        }


    }
}