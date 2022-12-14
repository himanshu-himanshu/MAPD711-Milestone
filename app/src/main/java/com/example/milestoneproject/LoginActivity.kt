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
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val sharedPrefFile = "userDetailPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val db = Firebase.firestore
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        registerNow.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            if (isEmpty(emailInputField.text) || isEmpty(
                    passwordInputField.text
                )
            ) {
                Toast.makeText(applicationContext, "Fields cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "Called login", Toast.LENGTH_SHORT)
                auth.signInWithEmailAndPassword(
                    emailInputField.text.toString(),
                    passwordInputField.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val editor: SharedPreferences.Editor = sharedPreferences.edit()
                            val docRef = db.collection("users")
                                .whereEqualTo("Email", emailInputField.text.toString())
                            docRef.get()
                                .addOnSuccessListener { document ->
                                    if (document != null) {
                                        val name = "${document.documents.get(0).data?.get("Name")}"
                                        editor.putString("name", name)
                                        editor.apply()
                                        editor.commit()
                                    } else {
                                        Toast.makeText(
                                            applicationContext,
                                            "Not Found",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                }

                            editor.putString("email", emailInputField.text.toString())
                            editor.apply()
                            editor.commit()
                            Toast.makeText(applicationContext, "Welcome to KwizApp", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            }
        }
    }
}