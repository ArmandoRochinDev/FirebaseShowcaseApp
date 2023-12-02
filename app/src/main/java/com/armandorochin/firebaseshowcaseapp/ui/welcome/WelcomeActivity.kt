package com.armandorochin.firebaseshowcaseapp.ui.welcome

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.armandorochin.firebaseshowcaseapp.R
import com.armandorochin.firebaseshowcaseapp.databinding.ActivityWelcomeBinding
import com.armandorochin.firebaseshowcaseapp.ui.login.LoginActivity
import com.armandorochin.firebaseshowcaseapp.ui.signin.SignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity(){

    private lateinit var binding: ActivityWelcomeBinding
    private val welcomeViewModel: WelcomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        welcomeViewModel.verifyUser()
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            btnLogin.setOnClickListener { welcomeViewModel.onLoginSelected() }
            btnSingIn.setOnClickListener { welcomeViewModel.onSignInSelected() }
        }
    }

    private fun initObservers() {
        welcomeViewModel.navigateToHome.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToHome()
            }
        })

        welcomeViewModel.navigateToLogin.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToLogin()
            }
        })
        welcomeViewModel.navigateToSignIn.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToSingIn()
            }
        })
    }


    private fun goToSingIn() {
        startActivity(SignInActivity.create(this))
    }

    private fun goToLogin() {
        startActivity(LoginActivity.create(this))
    }

    private fun goToHome() {
        Toast.makeText(this, R.string.feature_not_allowed, Toast.LENGTH_SHORT).show()
    }
}