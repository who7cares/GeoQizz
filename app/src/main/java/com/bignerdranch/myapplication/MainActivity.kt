package com.bignerdranch.myapplication
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast



class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private lateinit var questionTextView: TextView
    private lateinit var numberOfQuestionTextView: TextView

    private lateinit var nextButton: ImageView
    private lateinit var previousButton: ImageView

    private  val questionBank: List<Question> = listOf(
        Question(R.string.question_australia, false),
        Question(R.string.question_kama, true),
        Question(R.string.question_moon, true),
        Question(R.string.question_izhevsk, false),
        Question(R.string.question_dublin, false)
    )

    private var currentIndex = 0

    private var isAnswered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        questionTextView = findViewById(R.id.question_text_view)
        numberOfQuestionTextView = findViewById(R.id.question_number)

        nextButton = findViewById(R.id.right_arrow)
        previousButton = findViewById(R.id.left_arrow)


        // сообщение о верном ответе
        trueButton.setOnClickListener {
            if (!isAnswered) {
                chekAnswer(true)
                isAnswered = true
                disableButtons()  // Блокируем кнопки
            }
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

        previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
            updateQuestion()
        }

        // устанавливаем первое значение текста вопроса
        updateQuestion()

    }




    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        numberOfQuestionTextView.setText("№ вопроса: ${currentIndex + 1}")
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

        toast.setGravity(Gravity.TOP,0,250)
        toast.show()

    }

    // Блокируем кнопки ответов
    private fun disableButtons() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    // Включаем кнопки ответов
    private fun enableButtons() {
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }
}

}

