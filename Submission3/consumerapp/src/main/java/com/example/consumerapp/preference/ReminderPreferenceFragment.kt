package com.example.consumerapp.preference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.consumerapp.R
import com.example.consumerapp.receiver.AlarmReminderReceiver

class ReminderPreferenceFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var alarmReminderReceiver: AlarmReminderReceiver
    private lateinit var REMINDER: String
    private lateinit var isRemindedPreference: SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        alarmReminderReceiver = AlarmReminderReceiver()
        init()
        setSummaries()
    }

    private fun init() {
        REMINDER = resources.getString(R.string.key_reminder)

        isRemindedPreference = findPreference<SwitchPreference>(REMINDER) as SwitchPreference
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        isRemindedPreference.isCopyingEnabled = sh.getBoolean(REMINDER, false)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if(key == REMINDER) {
            isRemindedPreference.isCopyingEnabled = sharedPreferences.getBoolean(REMINDER, false)

            if(isRemindedPreference.isCopyingEnabled) {
                alarmReminderReceiver.setRepeatingAlarm(requireContext(), "09:00", getString(R.string.lets_find_popular_user_on_github))
            } else {
                alarmReminderReceiver.cancelAlarm(requireContext())
            }
        }
    }
}