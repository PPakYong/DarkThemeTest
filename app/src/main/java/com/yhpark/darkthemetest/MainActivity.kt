package com.yhpark.darkthemetest

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.yhpark.darkthemetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.themeText.text = themeToString(resources.configuration.uiMode)

        binding.themeDefaultSwitch.setOnClickListener(defaultListener)
        binding.themeDarkSwitch.setOnClickListener(darkListener)
        binding.themeWhiteSwitch.setOnClickListener(whiteListener)
    }

    private val defaultListener: OnClickListener =
        OnClickListener { buttonView ->
            binding.themeDarkSwitch.isChecked = false
            binding.themeWhiteSwitch.isChecked = false

            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        }

    private val darkListener: OnClickListener =
        OnClickListener { buttonView ->
            binding.themeDefaultSwitch.isChecked = false
            binding.themeWhiteSwitch.isChecked = false

            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        }

    private val whiteListener: OnClickListener =
        OnClickListener { buttonView ->
            binding.themeDefaultSwitch.isChecked = false
            binding.themeDarkSwitch.isChecked = false

            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        binding.themeText.text = themeToString(newConfig.uiMode)
    }

    private fun themeToString(uiMode: Int): String {
        return when (uiMode) {
            Configuration.UI_MODE_NIGHT_NO -> "UI_MODE_NIGHT_NO" // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> "UI_MODE_NIGHT_YES" // Night mode is active, we're using dark theme
            Configuration.UI_MODE_NIGHT_MASK -> "UI_MODE_NIGHT_MASK"
            Configuration.UI_MODE_NIGHT_UNDEFINED -> "UI_MODE_NIGHT_UNDEFINED"
            else -> ""
        }
    }
}