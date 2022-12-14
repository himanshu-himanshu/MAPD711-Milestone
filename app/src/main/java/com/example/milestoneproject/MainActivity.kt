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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.milestoneproject.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.startQuizBtn
import kotlinx.android.synthetic.main.activity_welcome.*

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "userDetailPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        startQuizBtn.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
        scoreCard.setOnClickListener {
            val intent = Intent(this, ScoreBoard::class.java)
            startActivity(intent)
        }

        aboutCard.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

        signoutBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Sign out")
            builder.setMessage("Are you sure you want to sign out?")

            builder.setPositiveButton("No") { dialog, which ->

            }

            builder.setNegativeButton("Logout") { dialog, which ->
                Firebase.auth.signOut()
                editor.remove("email")
                editor.apply()
                editor.commit()
                Toast.makeText(
                    applicationContext,
                    "See you soon!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            builder.show()
        }
    }
}