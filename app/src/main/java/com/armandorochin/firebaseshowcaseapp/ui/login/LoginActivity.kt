package com.armandorochin.firebaseshowcaseapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.armandorochin.firebaseshowcaseapp.R
import com.armandorochin.firebaseshowcaseapp.core.dismissKeyboard
import com.armandorochin.firebaseshowcaseapp.core.loseFocusAfterAction
import com.armandorochin.firebaseshowcaseapp.core.onTextChanged
import com.armandorochin.firebaseshowcaseapp.databinding.ActivityLoginBinding
import com.armandorochin.firebaseshowcaseapp.ui.home.HomeActivity
import com.armandorochin.firebaseshowcaseapp.ui.login.model.UserLogin
import com.armandorochin.firebaseshowcaseapp.ui.signin.SignInActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, LoginActivity::class.java)

    }

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.etEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etEmail.onTextChanged { onFieldChanged() }

        binding.etPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.etPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etPassword.onTextChanged { onFieldChanged() }

        binding.tvSignIn.setOnClickListener { loginViewModel.onSignInSelected() }

        binding.btnLogin.setOnClickListener {
            it.dismissKeyboard()
            loginViewModel.onLoginSelected(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun initObservers() {
        loginViewModel.navigateToDetails.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToHome()
            }
        }

        loginViewModel.navigateToSignIn.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToSignIn()
            }
        }

        loginViewModel.navigateToForgotPassword.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToForgotPassword()
            }
        }

        loginViewModel.showErrorDialog.observe(this) { userLogin ->
            if (userLogin.showErrorDialog) showErrorDialog(userLogin)
        }

        lifecycleScope.launchWhenStarted {
            loginViewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }
    }

    private fun updateUI(viewState: LoginViewState) {
        with(binding) {
            pbLoading.isVisible = viewState.isLoading
            tilEmail.error =
                if (viewState.isValidEmail) null else getString(R.string.login_error_mail)
            tilPassword.error =
                if (viewState.isValidPassword) null else getString(R.string.login_error_password)
        }
    }

    private fun onFieldChanged(hasFocus: Boolean = false) {
        if (!hasFocus) {
            loginViewModel.onFieldsChanged(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
    }

    private fun showErrorDialog(userLogin: UserLogin) {
//        ErrorDialog.create(
//            title = getString(R.string.login_error_dialog_title),
//            description = getString(R.string.login_error_dialog_body),
//            negativeAction = ErrorDialog.Action(getString(R.string.login_error_dialog_negative_action)) {
//                it.dismiss()
//            },
//            positiveAction = ErrorDialog.Action(getString(R.string.login_error_dialog_positive_action)) {
//                loginViewModel.onLoginSelected(
//                    userLogin.email,
//                    userLogin.password
//                )
//                it.dismiss()
//            }
//        ).show(dialogLauncher, this)
    }

    private fun goToForgotPassword() {
        Toast.makeText(this, R.string.feature_not_allowed, Toast.LENGTH_SHORT).show()
    }

    private fun goToSignIn() {
        startActivity(SignInActivity.create(this))
    }

    private fun goToHome() {
        startActivity(HomeActivity.create(this))
    }
}