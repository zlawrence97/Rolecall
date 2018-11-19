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

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginbutton: Button
    private lateinit var signup: Button
    private lateinit var saveUser: CheckBox
    private lateinit var savePassword: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginbutton = findViewById(R.id.login)
        signup = findViewById(R.id.signup)
        saveUser = findViewById(R.id.remuser)
        savePassword = findViewById(R.id.rempass)



    }
}
