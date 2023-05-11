package com.example.roombookingapp.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.roombookingapp.R
import com.example.roombookingapp.presentation.utils.extensions.showToastLong
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private val vmSignUpViewModel: SignUpViewModel by viewModel()

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
        initTextChangeListeners()
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

    private fun initTextChangeListeners() {
        etName.addTextChangedListener { name ->
            vmSignUpViewModel.nameLiveData.value = name.toString()
        }

        etSurname.addTextChangedListener { surname ->
            vmSignUpViewModel.surnameLiveData.value = surname.toString()
        }

        etEmail.addTextChangedListener { email ->
            vmSignUpViewModel.emailLiveData.value = email.toString()
        }

        etPassword.addTextChangedListener { password ->
            vmSignUpViewModel.passwordLiveData.value = password.toString()
        }
    }

    private fun initClickListeners() {
        val currentContext = context ?: return
        btnRegister.setOnClickListener {
            if (etName.text.toString().isEmpty()
                || etSurname.text.toString().isEmpty()
                || etEmail.text.toString().isEmpty()
                || etPassword.text.toString().isEmpty()
            ) {
                currentContext.showToastLong(R.string.please_fill_all_fields)
            } else {
                vmSignUpViewModel.registerUser()
            }
        }

        tvSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}