package com.sanket.androidplayground2.misc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.sanket.androidplayground2.databinding.ActivityEncryptedSharedPreferencesBinding

class EncryptedSharedPreferencesActivity : AppCompatActivity() {

    private val binding: ActivityEncryptedSharedPreferencesBinding by lazy {
        ActivityEncryptedSharedPreferencesBinding.inflate(
            layoutInflater
        )
    }
    private val sharedPrefsFile by lazy { "MY_ENCRYPTED_PREFS" }
    private val mainKeyAlias by lazy { MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC) }
    private val sharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            sharedPrefsFile,
            mainKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.apply {
            btnSave.setOnClickListener {
                if (dataIsValid()) {
                    saveDataToEncryptedSharedPrefs(etKey.text.toString(), etValue.text.toString())
                } else {
                    toast("Key and value should not be blank!")
                }
            }
            btnShow.setOnClickListener {
                if (keyIsInputAndValid()) {
                    toast(getEncryptedData(etKey.text.toString()))
                } else {
                    toast("Key is blank or not stored in preferences")
                }
            }
        }
    }

    private fun getEncryptedData(key: String) = sharedPreferences.getString(key, "")!!

    private fun keyIsInputAndValid() =
        binding.etKey.text.isNotBlank() and sharedPreferences.contains(binding.etKey.text.toString())

    private fun toast(message: String) {
        Toast.makeText(
            this@EncryptedSharedPreferencesActivity,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun dataIsValid() =
        (binding.etKey.text.isNotBlank() and binding.etValue.text.isNotBlank())

    private fun saveDataToEncryptedSharedPrefs(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
}