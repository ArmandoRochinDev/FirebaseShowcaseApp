package com.armandorochin.firebaseshowcaseapp.ui.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.armandorochin.firebaseshowcaseapp.R
import com.armandorochin.firebaseshowcaseapp.databinding.ActivityHomeBinding
import com.armandorochin.firebaseshowcaseapp.ui.welcome.WelcomeActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, HomeActivity::class.java)
    }

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailsToolbar)
        initUI()
        initObservers()
        homeViewModel.getUserInfo(this)
        homeViewModel.getProfilePicture()
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if(it){
            cameraLauncher.launch(homeViewModel.getUri())
        }
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()){
        if(it){
            homeViewModel.getUri().let {
                homeViewModel.uploadPicture()
            }
        }
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding){
            profilePicture.setOnClickListener {
                val permissionCheckResult = ContextCompat.checkSelfPermission(it.context, Manifest.permission.CAMERA)

                if(permissionCheckResult == PackageManager.PERMISSION_GRANTED){
                    cameraLauncher.launch(homeViewModel.getUri())
                }else{
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.action_logout -> {
                homeViewModel.onLogoutSelected()
                true
            }
            R.id.action_about -> {
                //TODO: open about activity
                Toast.makeText(this, R.string.feature_not_allowed, Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initObservers() {
        homeViewModel.navigateToWelcome.observe(this) {
            it.getContentIfNotHandled()?.let {
                goToWelcome()
            }
        }

        homeViewModel.userInfo.observe(this){
            it?.let {
                if(!it.showErrorDialog){
                    binding.tvNickname.text = it.nickname
                    binding.tvRealname.text = it.realname
                    binding.tvEmail.text = it.email
                    it.imgUrl?.let { img ->
                        Glide.with(this).load(img).into(binding.profilePicture)
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            homeViewModel.viewState.collect{
                updateUI(it)
            }
        }
    }

    private fun updateUI(viewState: HomeViewState){
        with(binding){
            tvNickname.isVisible = viewState.isValidUser
            tvRealname.isVisible = viewState.isValidUser
            tvEmail.isVisible = viewState.isValidUser
            profilePicture.isVisible = viewState.isValidUser
        }
    }

    private fun goToWelcome() {
        startActivity(WelcomeActivity.create(this))
    }
}