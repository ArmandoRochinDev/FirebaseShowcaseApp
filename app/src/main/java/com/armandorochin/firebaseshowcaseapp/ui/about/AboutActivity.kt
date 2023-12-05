package com.armandorochin.firebaseshowcaseapp.ui.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.armandorochin.firebaseshowcaseapp.R
import com.armandorochin.firebaseshowcaseapp.databinding.ActivityAboutBinding
import com.bumptech.glide.Glide

class AboutActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.detailsToolbar)
        supportActionBar?.title = getString(R.string.about_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupUI() {
        Glide.with(binding.profilePicture.context).load(profilePicturePath).into(binding.profilePicture)

        binding.ivGoogleplay.setOnClickListener { openUrl(googlePlayDevUrl) }
        binding.ivGithub.setOnClickListener { openUrl(githubProfileUrl) }
        binding.ivCv.setOnClickListener { openUrl(cvPdfUrl) }

        binding.tvAboutDescription.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun openUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> false
        }
    }

    companion object{
        const val profilePicturePath = "https://avatars.githubusercontent.com/u/88679335?v=4"
        const val googlePlayDevUrl = "https://play.google.com/store/apps/dev?id=4832117956294238601"
        const val githubProfileUrl = "https://github.com/ArmandoRochinDev"
        const val cvPdfUrl = "https://github.com/ArmandoRochinDev/ArmandoRochinDev/blob/main/CV2023.pdf"

        fun create(context: Context): Intent =
            Intent(context, AboutActivity::class.java)
    }
}