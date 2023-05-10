package com.example.roombookingapp.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.roombookingapp.R
import com.example.roombookingapp.presentation.rooms.RoomsFragment
import com.example.roombookingapp.presentation.signup.SignUpFragment
import com.google.android.material.textfield.TextInputEditText

class SignInFragment : Fragment() {

    private val flContainerID = R.id.fl_login_container

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initClickListeners()
    }

    private fun initViews(view: View) {
        with(view) {
            etEmail = findViewById(R.id.sign_in_et_email)
            etPassword = findViewById(R.id.sign_in_et_password)
            btnLogin = findViewById(R.id.sign_in_btn_login)
            tvSignUp = findViewById(R.id.sign_in_tv_sign_up)
        }
    }

    private fun initClickListeners() {
        btnLogin.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(flContainerID, RoomsFragment(), null)
                .commit()
        }

        tvSignUp.setOnClickListener {
            initSignUpForm()
        }
    }

    private fun initSignUpForm() {
        parentFragmentManager
            .beginTransaction()
            .replace(flContainerID, SignUpFragment(), null)
            .addToBackStack(null)
            .commit()
    }
}