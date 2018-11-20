package com.example.owner.rolecall.ui

import com.example.owner.rolecall.R
import android.content.Context
import android.content.Intent
import android.location.Address
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*

import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class LoginActivity : AppCompatActivity() {

    private val PREF_FILENAME = "project-2-zlawrence97"
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginbutton: Button
    private lateinit var signupbutton: Button
    private lateinit var saveUser: CheckBox
    private lateinit var savePassword: CheckBox
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Get a SharedPreferences object
        val preferences = getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE)

        progress = findViewById(R.id.progressBar)
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginbutton = findViewById(R.id.login)
        signupbutton = findViewById(R.id.signup)
        saveUser = findViewById(R.id.remuser)
        savePassword = findViewById(R.id.rempass)

        progress.visibility = View.INVISIBLE

        val isFirstRun = preferences.getBoolean("isFirstRun", true)
        if(isFirstRun){
            AlertDialog.Builder(this)
                .setTitle("Shabooya ROLECALL!")
                .setMessage("A new app to keep attendance.")
                .setPositiveButton("Let's Go"){
                        dialog, id -> dialog.dismiss()
                }
                .show()
        }
        preferences.edit().putBoolean("isFirstRun",false).apply()

        signupbutton.setOnClickListener {
            val username = usernameEditText.text.toString()


        }
    }
}
