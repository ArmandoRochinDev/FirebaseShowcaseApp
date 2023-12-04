package com.armandorochin.firebaseshowcaseapp.ui.welcome

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.armandorochin.firebaseshowcaseapp.databinding.ActivityWelcomeBinding
import com.armandorochin.firebaseshowcaseapp.ui.home.HomeActivity
import com.armandorochin.firebaseshowcaseapp.ui.login.LoginActivity
import com.armandorochin.firebaseshowcaseapp.ui.signin.SignInActivity
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity(){

    companion object {
        fun create(context: Context): Intent =
            Intent(context, WelcomeActivity::class.java)
    }

    private lateinit var binding: ActivityWelcomeBinding
    private val welcomeViewModel: WelcomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        welcomeViewModel.verifyUser()
        initUI()
        permissionRequest()
        //getToken()
    }

//    private fun getToken() {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener {
//            if (!it.isSuccessful){
//                return@addOnCompleteListener
//            }
//            val token = it.result//Token del cliente
//        }
//    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){}

    private fun permissionRequest(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
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
        welcomeViewModel.navigateToHome.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToHome()
            }
        }

        welcomeViewModel.navigateToLogin.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToLogin()
            }
        }
        welcomeViewModel.navigateToSignIn.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToSingIn()
            }
        }
    }


    private fun goToSingIn() {
        startActivity(SignInActivity.create(this))
    }

    private fun goToLogin() {
        startActivity(LoginActivity.create(this))
    }

    private fun goToHome() {
        startActivity(HomeActivity.create(this))
    }
}