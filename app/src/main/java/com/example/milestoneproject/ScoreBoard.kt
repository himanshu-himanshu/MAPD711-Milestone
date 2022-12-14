/*
* Author: Himanshu(301296001)& Gurminder(301294300)
* Assignment: Milestone Project
* Project Name: Quiz App
* Date: 12 Dec, 2022
* */

package com.example.milestoneproject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_score_board.*

class ScoreBoard : AppCompatActivity() {
    private val sharedPrefFile = "userDetailPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)
        val db = Firebase.firestore
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        var name: String? = sharedPreferences.getString("name", "admin")

        var htmlScore: String
        var cssScore: String
        var jsScore: String
        var androidScore: String

        val docRef = db.collection("users")
            .whereEqualTo("Name", name)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    htmlScore = "${document.documents.get(0).data?.get("HTML")}"
                    cssScore = "${document.documents.get(0).data?.get("CSS")}"
                    androidScore = "${document.documents.get(0).data?.get("ANDROID")}"
                    jsScore = "${document.documents.get(0).data?.get("JS")}"
                    if (htmlScore != "null") {
                        htmlText.text = "HTML: " + htmlScore + "/5"
                    } else {
                        htmlText.text = "HTML: 0/5"
                        //htmlText.visibility = View.GONE
                    }
                    if (cssScore != "null") {
                        cssText.text = "CSS : " + cssScore + "/5"
                    } else {
                        cssText.text = "CSS : 0/5"
                        //cssText.visibility = View.GONE
                    }
                    if (jsScore != "null") {
                        jsText.text = "JAVASCRIPT: " + jsScore + "/5"
                    } else {
                        jsText.text = "JAVASCRIPT: 0/5"
                        //jsText.visibility = View.GONE
                    }
                    if (androidScore != "null") {
                        androidText.text = "ANDROID: " + androidScore + "/5"
                    } else {
                        androidText.text = "ANDROID: 0/5"
                        //androidText.visibility = View.GONE
                    }

                } else {
                    Toast.makeText(
                        applicationContext,
                        "Not Found",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        nameText.text =
            "Hi, " + name + " have a look at your previous highscores and create new ones."
    }
}