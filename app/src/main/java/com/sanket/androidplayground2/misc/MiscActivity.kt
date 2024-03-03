package com.sanket.androidplayground2.misc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.custom.CustomThreadPoolActivity
import com.sanket.androidplayground2.databinding.ActivityMiscBinding

class MiscActivity : AppCompatActivity() {

    private val binding: ActivityMiscBinding by lazy { ActivityMiscBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initClickListener()
    }

    private fun initClickListener() {
        binding.apply {
            btnDynamicLauncherIconAndText.setOnClickListener { openActivity<DynamicLauncherIconAndTextActivity>() }
            btnEncryptedSharedPreferences.setOnClickListener { openActivity<EncryptedSharedPreferencesActivity>() }
            btnAddCalendarEvent.setOnClickListener { openActivity<AddCalendarEventActivity>() }
            btnRuntimePermissions.setOnClickListener { openActivity<RuntimePermissionsActivity>() }
            btnCustomThreadPoolService.setOnClickListener { openActivity<CustomThreadPoolActivity>() }
        }
    }
}