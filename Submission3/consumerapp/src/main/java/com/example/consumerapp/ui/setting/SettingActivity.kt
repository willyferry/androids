package com.example.consumerapp.ui.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_LOCALE_SETTINGS
import androidx.appcompat.app.AppCompatActivity
import com.example.consumerapp.R
import com.example.consumerapp.databinding.ActivitySettingBinding
import com.example.consumerapp.preference.ReminderPreferenceFragment
import com.example.consumerapp.ui.main.MainActivity

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.setting_holder, ReminderPreferenceFragment()).commit()

        binding.imgbtnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tvSettingLanguage.setOnClickListener {
            val mIntent = Intent(ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

    }
}