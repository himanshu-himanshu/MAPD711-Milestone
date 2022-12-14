/*
* Author: Himanshu(301296001)& Gurminder(301294300)
* Assignment: Milestone Project
* Project Name: Quiz App
* Date: 12 Dec, 2022
* */

package com.example.milestoneproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    //private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = Firebase.auth
        val db = Firebase.firestore

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        loginNow.setOnClickListener {
            moveToLogin()
        }

        signupBtn.setOnClickListener {
            if (isEmpty(nameInputText.text) || isEmpty(emailInputText.text) || isEmpty(
                    passwordInputText.text
                )
            ) {
                Toast.makeText(applicationContext, "Fields cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            } else {

                var email = emailInputText.text.toString()
                var name = nameInputText.text.toString()
                var password = passwordInputText.text.toString()

                val user = hashMapOf(
                    "Name" to name,
                    "Email" to email,
                )

                db.collection("users").whereEqualTo("Email", email).get()
                    .addOnSuccessListener { document ->
                        if (document.isEmpty) {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            baseContext, "Authentication success, login now",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        db.collection("users").add(user)
                                            .addOnSuccessListener {
                                                moveToLogin()
                                            }

                                    } else {
                                        Toast.makeText(
                                            baseContext, "Authentication failed.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                        } else {
                            Toast.makeText(
                                applicationContext,
                                "User already exists, please login",
                                Toast.LENGTH_SHORT
                            ).show()
                            moveToLogin()
                        }
                    }
            }
        }
    }

    fun moveToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
