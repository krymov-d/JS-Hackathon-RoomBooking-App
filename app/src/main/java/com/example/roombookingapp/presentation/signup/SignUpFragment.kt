package com.example.roombookingapp.presentation.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.roombookingapp.R
import com.example.roombookingapp.presentation.utils.extensions.showSnackBar
import com.example.roombookingapp.presentation.utils.extensions.showSnackBarWithAction
import com.google.android.material.color.MaterialColors
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private val vmSignUp: SignUpViewModel by viewModel()

    private lateinit var etName: TextInputEditText
    private lateinit var etSurname: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnRegister: Button
    private lateinit var progressIndicator: CircularProgressIndicator
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

        val currentContext = context ?: return

        initViews(view)
        initTextChangeListeners()
        initClickListeners(currentContext)
        initObservers(currentContext)
    }

    private fun initViews(view: View) {
        with(view) {
            etName = findViewById(R.id.sign_up_et_name)
            etSurname = findViewById(R.id.sign_up_et_surname)
            etEmail = findViewById(R.id.sign_up_et_email)
            etPassword = findViewById(R.id.sign_up_et_password)
            btnRegister = findViewById(R.id.sign_up_btn_register)
            progressIndicator = findViewById(R.id.sign_up_progress_indicator)
            tvSignIn = findViewById(R.id.sign_up_tv_sign_in)
        }
    }

    private fun initTextChangeListeners() {
        etName.addTextChangedListener { name ->
            vmSignUp.nameLiveData.value = name.toString()
        }

        etSurname.addTextChangedListener { surname ->
            vmSignUp.surnameLiveData.value = surname.toString()
        }

        etEmail.addTextChangedListener { email ->
            vmSignUp.emailLiveData.value = email.toString()
        }

        etPassword.addTextChangedListener { password ->
            vmSignUp.passwordLiveData.value = password.toString()
        }
    }

    private fun initClickListeners(currentContext: Context) {
        btnRegister.setOnClickListener {
            if (etName.text.toString().isEmpty()
                || etSurname.text.toString().isEmpty()
                || etEmail.text.toString().isEmpty()
                || etPassword.text.toString().isEmpty()
            ) {
                currentContext.showSnackBar(
                    view = it,
                    messageStringId = R.string.please_fill_out_all_required_fields
                )
            } else {
                vmSignUp.registerUser()
            }
        }

        tvSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initObservers(currentContext: Context) {
        vmSignUp.progressLiveData.observe(viewLifecycleOwner) { isInProgress ->
            if (isInProgress) {
                btnRegister.isEnabled = false
                btnRegister.setTextColor(resources.getColor(R.color.ui_03, null))
                progressIndicator.visibility = View.VISIBLE
            } else {
                btnRegister.isEnabled = true
                btnRegister.setTextColor(MaterialColors.getColor(btnRegister, R.attr.ui_01))
                progressIndicator.visibility = View.INVISIBLE
            }
        }

        vmSignUp.registrationStatusLiveData.observe(viewLifecycleOwner) { isRegistered ->
            if (isRegistered) {
                currentContext.showSnackBar(
                    view = btnRegister,
                    messageStringId = R.string.registration_completed_successfully_please_log_in
                )
                parentFragmentManager.popBackStack()
            } else {
                currentContext.showSnackBarWithAction(
                    view = btnRegister,
                    messageStringId = R.string.registration_failed,
                    actionStringId = R.string.retry
                ) {
                    vmSignUp.registerUser()
                }
            }
        }
    }
}