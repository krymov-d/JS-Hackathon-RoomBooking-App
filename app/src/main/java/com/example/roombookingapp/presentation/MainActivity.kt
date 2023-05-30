package com.example.roombookingapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roombookingapp.R
import com.example.roombookingapp.presentation.signin.SignInFragment

class MainActivity : AppCompatActivity() {

    private val flContainerID = R.id.fl_login_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSignInForm()
    }

    private fun initSignInForm() {
        supportFragmentManager
            .beginTransaction()
            .add(flContainerID, SignInFragment(), null)
            .commit()
    }
}