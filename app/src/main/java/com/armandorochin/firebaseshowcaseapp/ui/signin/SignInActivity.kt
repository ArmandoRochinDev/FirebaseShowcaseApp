package com.armandorochin.firebaseshowcaseapp.ui.signin

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
import com.armandorochin.firebaseshowcaseapp.databinding.ActivitySignInBinding
import com.armandorochin.firebaseshowcaseapp.ui.login.LoginActivity
import com.armandorochin.firebaseshowcaseapp.ui.signin.model.UserSignIn
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, SignInActivity::class.java)
    }

    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {

        binding.etEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etEmail.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etEmail.onTextChanged { onFieldChanged() }

        binding.etNickname.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etNickname.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etNickname.onTextChanged { onFieldChanged() }

        binding.etRealName.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etRealName.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etRealName.onTextChanged { onFieldChanged() }

        binding.etPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etPassword.onTextChanged { onFieldChanged() }

        binding.etRepeatPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.etRepeatPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etRepeatPassword.onTextChanged { onFieldChanged() }


        with(binding) {
            btnCreateAccount.setOnClickListener {
                it.dismissKeyboard()
                signInViewModel.onSignInSelected(
                    UserSignIn(
                        realName = binding.etRealName.text.toString(),
                        nickName = binding.etNickname.text.toString(),
                        email = binding.etEmail.text.toString(),
                        password = binding.etPassword.text.toString(),
                        passwordConfirmation = binding.etRepeatPassword.text.toString()
                    )
                )
            }
        }
    }

    private fun initObservers() {
        signInViewModel.navigateToLogin.observe(this) {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this@SignInActivity, R.string.sigin_user_created, Toast.LENGTH_SHORT).show()
                goToLogin()
            }
        }

        lifecycleScope.launchWhenStarted {
            signInViewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }

        signInViewModel.showErrorDialog.observe(this) { showError ->
            if (showError) showErrorDialog()
        }
    }

    private fun showErrorDialog() {
    }

    private fun updateUI(viewState: SignInViewState) {
        with(binding) {
            pbLoading.isVisible = viewState.isLoading
            binding.tilEmail.error =
                if (viewState.isValidEmail) null else getString(R.string.signin_error_mail)
            binding.tilNickname.error =
                if (viewState.isValidNickName) null else getString(R.string.signin_error_nickname)
            binding.tilRealName.error =
                if (viewState.isValidRealName) null else getString(R.string.signin_error_realname)
            binding.tilPassword.error =
                if (viewState.isValidPassword) null else getString(R.string.signin_error_password)
            binding.tilRepeatPassword.error =
                if (viewState.isValidPassword) null else getString(R.string.signin_error_password)
        }
    }

    private fun onFieldChanged(hasFocus: Boolean = false) {
        if (!hasFocus) {
            signInViewModel.onFieldsChanged(
                UserSignIn(
                    realName = binding.etRealName.text.toString(),
                    nickName = binding.etNickname.text.toString(),
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString(),
                    passwordConfirmation = binding.etRepeatPassword.text.toString()
                )
            )
        }
    }

    private fun goToLogin() {
        startActivity(LoginActivity.create(this))
    }
}