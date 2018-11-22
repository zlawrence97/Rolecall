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

import com.crashlytics.android.Crashlytics
import com.example.owner.rolecall.ui.bluetoothclass.CheckinActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

import java.util.*

class SignupActivity: AppCompatActivity() {

    private lateinit var submitbutton: Button
    private lateinit var progress: ProgressBar
    private lateinit var emailEditText: EditText
    private lateinit var newEditText: EditText
    private lateinit var confirmEditText: EditText
    private lateinit var crnEditText: EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        submitbutton = findViewById(R.id.submit)
        progress = findViewById(R.id.progressBar)
        emailEditText = findViewById(R.id.email)
        crnEditText = findViewById(R.id.classid)
        newEditText = findViewById(R.id.firstpass)
        confirmEditText = findViewById(R.id.confirmpass)

        emailEditText.addTextChangedListener(textWatcher)
        newEditText.addTextChangedListener(textWatcher)
        confirmEditText.addTextChangedListener(textWatcher)
        crnEditText.addTextChangedListener(textWatcher)
        progress.visibility = View.INVISIBLE

        submitbutton.setOnClickListener {
            if(newEditText.toString() != confirmEditText.toString()){
                AlertDialog.Builder(this)
                    .setTitle("Mismatch!")
                    .setMessage("The passwords you entered do not match :( Try typing them in again.")
                    .setPositiveButton("Okay"){
                            dialog, id -> dialog.dismiss()
                    }
                    .show()
            }else{
                /* TODO:
                 * If username is empty then launch intent
                 * Otherwise, verify username is not in db and send
                 * entered username to Signup intent
                 */
                val email = emailEditText.toString()
                val crn = crnEditText.toString()
                val password = confirmEditText.toString()

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    //TODO: Add 'teacher' check to send to AdminActivity
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        if (user != null) {
                            progress.visibility = View.VISIBLE

                            val intent = Intent(this, SignupActivity::class.java)
                            intent.putExtra(CheckinActivity.USER,email)
                            intent.putExtra(CheckinActivity.CRN,crn)
                            startActivity(intent)
                        }
                    } else {
                        val exception = task.exception
                        Toast.makeText(this, "Account creation failed! $exception", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val emailText: String = emailEditText.text.toString()
            val newpassText: String = newEditText.text.toString()
            val confirmText: String = confirmEditText.text.toString()
            val crnText: String = crnEditText.text.toString()
            submitbutton.isEnabled = emailText.isNotEmpty() && newpassText.isNotEmpty() && confirmText.isNotEmpty() && crnText.isNotEmpty()
        }
    }
}