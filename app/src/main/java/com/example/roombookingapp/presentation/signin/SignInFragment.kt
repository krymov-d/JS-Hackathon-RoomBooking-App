package com.example.roombookingapp.presentation.signin

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
import com.example.roombookingapp.presentation.rooms.RoomsFragment
import com.example.roombookingapp.presentation.signup.SignUpFragment
import com.example.roombookingapp.presentation.utils.extensions.showSnackBar
import com.example.roombookingapp.presentation.utils.extensions.showSnackBarWithAction
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private val flContainerID = R.id.fl_login_container

    private val vmSignIn: SignInViewModel by viewModel()

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var progressIndicator: CircularProgressIndicator
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

        val currentContext = context ?: return

        initViews(view)
        restoreAvailableValues()
        initTextChangeListeners()
        initClickListeners(currentContext)
        initObservers(currentContext)
    }

    private fun initViews(view: View) {
        with(view) {
            etEmail = findViewById(R.id.sign_in_et_email)
            etPassword = findViewById(R.id.sign_in_et_password)
            btnLogin = findViewById(R.id.sign_in_btn_login)
            progressIndicator = findViewById(R.id.sign_in_progress_indicator)
            tvSignUp = findViewById(R.id.sign_in_tv_sign_up)
        }
    }

    private fun restoreAvailableValues() {
        etEmail.setText(vmSignIn.emailLiveData.value)
        etPassword.setText(vmSignIn.passwordLiveData.value)
    }

    private fun initTextChangeListeners() {
        etEmail.addTextChangedListener { email ->
            vmSignIn.emailLiveData.value = email.toString()
        }

        etPassword.addTextChangedListener { password ->
            vmSignIn.passwordLiveData.value = password.toString()
        }
    }

    private fun initClickListeners(currentContext: Context) {
        btnLogin.setOnClickListener {
            if (etEmail.text.toString().isEmpty() || etPassword.text.toString().isEmpty()) {
                currentContext.showSnackBar(
                    view = it,
                    messageStringId = R.string.please_fill_all_fields
                )
            } else {
                vmSignIn.loginUser()
            }
        }

        tvSignUp.setOnClickListener {
            initSignUpFragment()
        }
    }

    private fun initSignUpFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(flContainerID, SignUpFragment(), null)
            .addToBackStack(null)
            .commit()
    }

    private fun initObservers(currentContext: Context) {
        vmSignIn.progressLiveData.observe(viewLifecycleOwner) { isInProgress ->
            if (isInProgress) {
                btnLogin.isEnabled = false
                btnLogin.setTextColor(resources.getColor(R.color.ui_03, null))
                progressIndicator.visibility = View.VISIBLE
            } else {
                btnLogin.isEnabled = true
                btnLogin.setTextColor(resources.getColor(R.color.ui_01, null))
                progressIndicator.visibility = View.INVISIBLE
            }
        }

        vmSignIn.loginStatusLiveData.observe(viewLifecycleOwner) { isLoggedIn ->
            if (!isLoggedIn) {
                currentContext.showSnackBar(
                    view = btnLogin,
                    messageStringId = R.string.login_successful
                )

                initRoomsFragment()

            } else {
                currentContext.showSnackBarWithAction(
                    view = btnLogin,
                    messageStringId = R.string.login_failed,
                    actionStringId = R.string.retry
                ) {
                    vmSignIn.loginUser()
                }
            }
        }
    }

    private fun initRoomsFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(flContainerID, RoomsFragment(), null)
            .commit()
    }
}