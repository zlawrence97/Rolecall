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

import java.util.*

class CheckinActivity {

    companion object {
        // ... because we want this constant to be shared with another class
        val CRN = "CRN"
        val USER = "USERNAME"

    }

    /*
     * TODO:
     * Student - Search for available Nearby bluetooth signal
     * Teacher - Start hub for bluetooth signin
     */
}