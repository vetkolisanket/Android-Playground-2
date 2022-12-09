package com.sanket.androidplayground2.misc

import android.content.ComponentName
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sanket.androidplayground2.OneLauncherAlias
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.ThreeLauncherAlias
import com.sanket.androidplayground2.TwoLauncherAlias
import com.sanket.androidplayground2.databinding.ActivityDynamicLauncherIconAndTextBinding

class DynamicLauncherIconAndTextActivity : AppCompatActivity() {

    private val binding: ActivityDynamicLauncherIconAndTextBinding by lazy {
        ActivityDynamicLauncherIconAndTextBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.apply {
            iBtnOne.setOnClickListener {
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        OneLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
                )
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        TwoLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        ThreeLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )
                Toast.makeText(
                    this@DynamicLauncherIconAndTextActivity,
                    getString(R.string.launcher_one_application_success),
                    Toast.LENGTH_SHORT
                ).show()
            }
            iBtnTwo.setOnClickListener {
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        OneLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        TwoLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
                )
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        ThreeLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )

                Toast.makeText(
                    this@DynamicLauncherIconAndTextActivity,
                    getString(R.string.launcher_two_application_success),
                    Toast.LENGTH_SHORT
                ).show()
            }
            iBtnThree.setOnClickListener {
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        OneLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        TwoLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )
                packageManager.setComponentEnabledSetting(
                    ComponentName(
                        this@DynamicLauncherIconAndTextActivity,
                        ThreeLauncherAlias::class.java
                    ), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
                )

                Toast.makeText(
                    this@DynamicLauncherIconAndTextActivity,
                    getString(R.string.launcher_three_application_success),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}