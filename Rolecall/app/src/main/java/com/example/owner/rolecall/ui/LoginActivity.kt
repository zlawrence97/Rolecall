package com.example.owner.rolecall.ui

import com.example.owner.rolecall.R
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.analytics.FirebaseAnalytics
import com.crashlytics.android.Crashlytics

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

import java.util.*

class LoginActivity : AppCompatActivity() {

    private val PREF_FILENAME = "project-2-zlawrence97"
    private val PREF_SAVED_USERNAME = "SAVED_USERNAME"
    private val PREF_SAVED_PASSWORD = "SAVED_PASSWORD"
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginbutton: Button
    private lateinit var signupbutton: Button
    private lateinit var saveUser: CheckBox
    private lateinit var savePassword: CheckBox
    private lateinit var progress: ProgressBar
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Get a SharedPreferences object
        val preferences = getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE)

        progress = findViewById(R.id.progressBar)
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginbutton = findViewById(R.id.login)
        signupbutton = findViewById(R.id.signup)
        saveUser = findViewById(R.id.remuser)
        savePassword = findViewById(R.id.rempass)

        usernameEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)
        progress.visibility = View.INVISIBLE

        val savedUsername = preferences.getString(PREF_SAVED_USERNAME,"")
        val savedPassword = preferences.getString(PREF_SAVED_PASSWORD,"")
        usernameEditText.setText(savedUsername)
        passwordEditText.setText(savedPassword)

        val isFirstRun = preferences.getBoolean("isFirstRun", true)
        if(isFirstRun){
            AlertDialog.Builder(this)
                .setTitle("Shabooya Rolecall!")
                .setMessage("A new app to keep attendance.")
                .setPositiveButton("Let's Go"){
                        dialog, id -> dialog.dismiss()
                }
                .show()
        }
        preferences.edit().putBoolean("isFirstRun",false).apply()

        signupbutton.setOnClickListener {
            progress.visibility = View.VISIBLE

            val intent = Intent(this, SignupActivity::class.java)

            progress.visibility = View.INVISIBLE
            startActivity(intent)

        }

        loginbutton.setOnClickListener {
            progress.visibility = View.VISIBLE
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            /* TODO:
             * Credential verfication and send to checkin page
             */
            firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAnalytics.logEvent("login_success", null)
                    val user = firebaseAuth.currentUser
                    if (user != null) {
                        Toast.makeText(this, "Logged in as ${user.email}!", Toast.LENGTH_SHORT).show()
                    }
                    preferences.edit().putString(PREF_SAVED_USERNAME, username).apply()
                    // TODO: Send to Checkin Activity
                    AlertDialog.Builder(this)
                        .setTitle("SUCCESS")
                        .setMessage("The Checkin activity would be launched if it was running :/")
                        .setPositiveButton("Sad, but yay!"){
                                dialog, id -> dialog.dismiss()
                        }
                        .show()
                    /*val intent = Intent(this, CheckinActivity::class.java)
                    intent.putExtra(CheckinActivity.CRN, user.)
                    intent.putExtra(CheckinActivity.USERNAME, user.)
                    startActivity(intent)
                    */
                } else {
                    val exception = task.exception
                    val errorType = if (exception is FirebaseAuthInvalidCredentialsException)
                        "invalid credentials" else "network connection"

                    val bundle = Bundle()
                    bundle.putString("error_type", errorType)
                    firebaseAnalytics.logEvent("login_failed", bundle)
                    Toast.makeText(this, "Login failed: $exception", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val usernameText: String = usernameEditText.text.toString()
            val passwordText: String = passwordEditText.text.toString()
            loginbutton.isEnabled = passwordText.isNotEmpty() && usernameText.isNotEmpty()
            signupbutton.isEnabled = passwordText.isNotEmpty() && usernameText.isNotEmpty()
        }
    }
}
