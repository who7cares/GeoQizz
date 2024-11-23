package com.bignerdranch.myapplication
import android.graphics.Color
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
    private lateinit var userScoreText: TextView

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
    private var userScore = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        userScoreText = findViewById(R.id.user_score)


        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        questionTextView = findViewById(R.id.question_text_view)
        numberOfQuestionTextView = findViewById(R.id.question_number)

        nextButton = findViewById(R.id.right_arrow)
        previousButton = findViewById(R.id.left_arrow)


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

        previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
            updateQuestion()
        }

        // устанавливаем первое значение текста вопроса
        updateQuestion()

    }


    private fun updateQuestion() {


        // устанавливаем активность кнопки для нового вопроса
        trueButton.isEnabled = questionBank[currentIndex].buttonState
        falseButton.isEnabled = questionBank[currentIndex].buttonState

        // устанавливаем новый вопрос из листа вопросов
        questionTextView.setText(questionBank[currentIndex].textResId)
        // устанавливаем номер вопроса
        numberOfQuestionTextView.setText("№ вопроса: ${currentIndex + 1}")
    }

    // проверяем ответ пользователя и выводим Toast
    private fun chekAnswer(userAnswer: Boolean) {


        // блокируем кнопки посл ответа пользователя
        questionBank[currentIndex].buttonState = false

        trueButton.isEnabled = questionBank[currentIndex].buttonState
        falseButton.isEnabled = questionBank[currentIndex].buttonState

        val correctAnswer = questionBank[currentIndex].answer
        val messageResId: Int

        if (userAnswer == correctAnswer) {
            messageResId = R.string.correct_toast
        } else {
            messageResId = R.string.wrong_toast
        }

        val toast = Toast.makeText(
            this,
            messageResId,
            Toast.LENGTH_SHORT
        )

        toast.show()
    }

    private fun userScore(answer: Boolean) {
        if (answer) {
            userScore++
        }

        if (currentIndex == questionBank.size) {

        }

    }

}



