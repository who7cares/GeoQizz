package com.bignerdranch.myapplication
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private  val questionBank: List<Question> = listOf(
        Question(R.string.question_australia, false),
        Question(R.string.question_kama, true),
        Question(R.string.question_moon, true),
        Question(R.string.question_izhevsk, false),
        Question(R.string.question_dublin, false)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        nextButton = findViewById(R.id.next_button)
        questionTextView= findViewById(R.id.question_text_view)

        // сообщение о верном ответе
        trueButton.setOnClickListener {
            chekAnswer(true)
        }

        // сообщение о неверном ответе
        falseButton.setOnClickListener {
            chekAnswer(false)
        }

        // кнопка далее
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        // устанавливаем первое значение текста вопроса
        updateQuestion()

    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    // проверяем ответ пользователя и выводим Toast
    private fun chekAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.wrong_toast
        }

        val toast = Toast.makeText(
            this,
            messageResId,
            Toast.LENGTH_SHORT
        )

        toast.setGravity(Gravity.TOP or Gravity.LEFT, 0, 500)
        toast.show()

    }

}