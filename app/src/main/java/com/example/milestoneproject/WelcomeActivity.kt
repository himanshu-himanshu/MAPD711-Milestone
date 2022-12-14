/*
* Author: Himanshu(301296001)& Gurminder(301294300)
* Assignment: Milestone Project
* Project Name: Quiz App
* Date: 12 Dec, 2022
* */

package com.example.milestoneproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    private val sharedPrefFile = "userDetailPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val db = Firebase.firestore
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        var email: String? = sharedPreferences.getString("email", "admin")
        var langSelected: String = ""

        db.collection("users").whereEqualTo("Email", email).get()
            .addOnCompleteListener {
                var name: String =
                    "Welcome, " + it.result.documents.get(0).data?.get("Name").toString()
            }

        htmlCard.setOnClickListener {
            setOtherWhite()
            htmlCard.setBackgroundResource(R.drawable.card_round_selected);
            langSelected = "HTML"
        }

        cssCard.setOnClickListener {
            setOtherWhite()
            cssCard.setBackgroundResource(R.drawable.card_round_selected);
            langSelected = "CSS"
        }

        androidCard.setOnClickListener {
            setOtherWhite()
            androidCard.setBackgroundResource(R.drawable.card_round_selected);
            langSelected = "ANDROID"
        }

        jsCard.setOnClickListener {
            setOtherWhite()
            jsCard.setBackgroundResource(R.drawable.card_round_selected);
            langSelected = "JS"
        }

        backToHomeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        startQuizBtn.setOnClickListener {
            if (langSelected.isEmpty()) {
                Toast.makeText(applicationContext, "Please select category", Toast.LENGTH_LONG)
                    .show();
            } else {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("Language", langSelected)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setOtherWhite() {
        htmlCard.setBackgroundResource(R.drawable.card_round_white);
        cssCard.setBackgroundResource(R.drawable.card_round_white);
        androidCard.setBackgroundResource(R.drawable.card_round_white);
        jsCard.setBackgroundResource(R.drawable.card_round_white);
    }
}