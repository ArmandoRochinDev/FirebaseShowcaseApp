package com.armandorochin.firebaseshowcaseapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.armandorochin.firebaseshowcaseapp.R
import com.armandorochin.firebaseshowcaseapp.databinding.ActivityHomeBinding
import com.armandorochin.firebaseshowcaseapp.ui.welcome.WelcomeActivity
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
        initObservers()
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
    }

    private fun goToWelcome() {
        startActivity(WelcomeActivity.create(this))
    }
}