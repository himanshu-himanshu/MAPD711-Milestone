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
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    private val sharedPrefFile = "userDetailPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val db = Firebase.firestore

        var isHtmlHighScore: Boolean = true
        var isCssHighScore: Boolean = true
        var isJsHighScore: Boolean = true
        var isAndroidHighScore: Boolean = true

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        var email: String? = sharedPreferences.getString("email", "admin")
        var name: String? = sharedPreferences.getString("name", "admin")

        val score = intent.getStringExtra("Score")
        val totalQues = intent.getStringExtra("TotalQues")
        val lan = intent.getStringExtra("Language")
        var id: String? = ""

        db.collection("users")
            .whereEqualTo("Email", email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    id = document.id
                }
            }

        val docRef = db.collection("users")
            .whereEqualTo("Name", name)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    var htmlScore = "${document.documents.get(0).data?.get("HTML")}"
                    var cssScore = "${document.documents.get(0).data?.get("CSS")}"
                    var androidScore = "${document.documents.get(0).data?.get("ANDROID")}"
                    var jsScore = "${document.documents.get(0).data?.get("JS")}"

                    if (htmlScore != "null" && htmlScore > score.toString()) {
                        isHtmlHighScore = false
                    }

                    if (cssScore != "null" && cssScore > score.toString()) {
                        isCssHighScore = false
                    }

                    if (jsScore != "null" && jsScore > score.toString()) {
                        isJsHighScore = false
                    }

                    if (androidScore != "null" && androidScore > score.toString()) {
                        isAndroidHighScore = false
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

        textScore.text = "$score" + "/" + "$totalQues"
        textCongo.text = "Congrats! $name"

        homeBtn.setOnClickListener {
            when (lan) {
                "HTML" -> {
                    if (isHtmlHighScore) {
                        db.collection("users").document(id.toString())
                            .update("HTML", score)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    baseContext, "Wow! it's new high score",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    baseContext, "Error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }

                }
                "CSS" -> {
                    if (isCssHighScore) {
                        db.collection("users").document(id.toString())
                            .update("CSS", score)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    baseContext, "Added",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    baseContext, "Error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                }
                "JS" -> {
                    if (isJsHighScore) {
                        db.collection("users").document(id.toString())
                            .update("JS", score)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    baseContext, "Added",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    baseContext, "Error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }

                }
                "ANDROID" -> {
                    if (isAndroidHighScore) {
                        db.collection("users").document(id.toString())
                            .update("ANDROID", score)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    baseContext, "Added",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    baseContext, "Error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }

                }
            }
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}