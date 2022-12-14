/*
* Author: Himanshu(301296001)& Gurminder(301294300)
* Assignment: Milestone Project
* Project Name: Quiz App
* Date: 12 Dec, 2022
* */

package com.example.milestoneproject

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private var questionList: ArrayList<QuestionData>? = null
    private var currentPos: Int = 1
    private var selectedOption: Int = 0
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val lang = intent.getStringExtra("Language")

        when (lang) {
            "HTML" -> questionList = setData.getHtmlQuestion()
            "CSS" ->   questionList = setData.getCssQuestion()
            "JS" ->  questionList = setData.getJsQuestion()
            "ANDROID" -> questionList = setData.getAndroidQuestion()
        }

        setQuestion()

        option1.setOnClickListener {
            selectedStyle(option1, 1)
        }
        option2.setOnClickListener {
            selectedStyle(option2, 2)
        }
        option3.setOnClickListener {
            selectedStyle(option3, 3)
        }
        option4.setOnClickListener {
            selectedStyle(option4, 4)
        }

        nextBtn.setOnClickListener {
            if (selectedOption != 0) {
                val que = questionList!![currentPos - 1]
                if (selectedOption != que.option_ans) {
                    setOptionColor(selectedOption, R.drawable.wrong_option)
                } else {
                    score++
                }
                setOptionColor(que.option_ans, R.drawable.correct_option)
                if (currentPos == questionList!!.size) {
                    nextBtn.text = "FINISH"
                } else {
                    nextBtn.text = "NEXT"
                }
            } else {
                currentPos++
                when {
                    currentPos <= questionList!!.size -> {
                        setQuestion()
                        nextBtn.text = "CHECk"
                    }
                    else -> {
                        var intent = Intent(this, ScoreActivity::class.java)
                        intent.putExtra("TotalQues", questionList!!.size.toString())
                        intent.putExtra("Score", score.toString())
                        intent.putExtra("Language", lang.toString())
                        startActivity(intent)
                        finish()
                    }
                }
            }
            selectedOption = 0

        }
    }

    fun setOptionColor(opt: Int, color: Int) {
        when (opt) {
            1 -> {
                option1.background = ContextCompat.getDrawable(this, color)
            }
            2 -> {
                option2.background = ContextCompat.getDrawable(this, color)
            }
            3 -> {
                option3.background = ContextCompat.getDrawable(this, color)
            }
            4 -> {
                option4.background = ContextCompat.getDrawable(this, color)
            }
        }
    }

    fun setQuestion() {

        var que = questionList!![currentPos - 1]

        setOptionStyle()

        progressBar.progress = currentPos
        progressBar.max = questionList!!.size
        progressText.text = "${currentPos}" + "/" + "${questionList!!.size}"

        questionText.text = que.question
        option1.text = que.option_one
        option2.text = que.option_two
        option3.text = que.option_three
        option4.text = que.option_four

    }

    fun setOptionStyle() {
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, option1)
        optionList.add(1, option2)
        optionList.add(2, option3)
        optionList.add(3, option4)

        for (opt in optionList) {
            opt.setTextColor(Color.parseColor("#333333"))
            opt.background = ContextCompat.getDrawable(this, R.drawable.question_option)
            opt.typeface = Typeface.DEFAULT
        }
    }

    fun selectedStyle(view: TextView, opt: Int) {
        setOptionStyle()
        selectedOption = opt
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#333333"))
    }
}