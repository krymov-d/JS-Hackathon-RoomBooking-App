package com.example.roombookingapp.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.roombookingapp.R
import com.google.android.material.textfield.TextInputEditText

class FragmentSignUp : Fragment() {

    private lateinit var etName: TextInputEditText
    private lateinit var etSurname: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnRegister: Button
    private lateinit var tvSignIn: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initClickListeners()
    }

    private fun initViews(view: View) {
        with(view) {
            etName = findViewById(R.id.sign_up_et_name)
            etSurname = findViewById(R.id.sign_up_et_surname)
            etEmail = findViewById(R.id.sign_up_et_email)
            etPassword = findViewById(R.id.sign_up_et_password)
            btnRegister = findViewById(R.id.sign_up_btn_register)
            tvSignIn = findViewById(R.id.sign_up_tv_sign_in)
        }
    }

    private fun initClickListeners() {
        btnRegister.setOnClickListener {

        }

        tvSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}