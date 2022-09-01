package com.ele.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //Lateinit is a keyword that is used to declare a variable that will be initialized later.
    private lateinit var btYes: Button
    private lateinit var btNo: Button
    private lateinit var btNext: Button
    private lateinit var btBack: Button
    private lateinit var tvQuestion: TextView
    var index: Int = 0

    private lateinit var question: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
        loadQuestions()

    }

    private fun loadQuestions() {
        question = ArrayList()
        question.add(Question("¿Es Lima la capital de Perú?", true))
        question.add(Question("¿Es Buenos Aires la capital de Ecuador?", false))
        question.add(Question("¿Es Caracas la capital de Venezuela?", true))
    }

    private fun initListeners() {
        btYes.setOnClickListener {
            checkAnswer(true)
        }
        btNo.setOnClickListener {
            checkAnswer(false)
        }
        btNext.setOnClickListener {
            index++
            if (index == question.size) {
                index = 0
            }
            showQuestion()
        }
        btBack.setOnClickListener {
            /*
            Ciclo infinito atrás
            index--
            if (index < 0) {
                index = question.size - 1
            }
            showQuestion()
            --------------------------------------------------------------
            Limite atrás: */
            if (index == 0) {
                Toast.makeText(this, "No hay preguntas anteriores", Toast.LENGTH_SHORT).show()
            } else {
                index--
                showQuestion()
            }
        }

    }

    private fun checkAnswer(b: Boolean) {
        if (b == question[index].answer) {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showQuestion() {
        tvQuestion.text = question[index].sentence
    }

    private fun initViews() {
        btYes = findViewById(R.id.bt_yes)
        btNo = findViewById(R.id.bt_no)
        btNext = findViewById(R.id.bt_next)
        tvQuestion = findViewById(R.id.tv_question)
        btBack = findViewById(R.id.bt_back)
    }
}
