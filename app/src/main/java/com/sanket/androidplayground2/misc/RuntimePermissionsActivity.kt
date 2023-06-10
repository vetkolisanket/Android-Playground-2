package com.sanket.androidplayground2.misc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.databinding.ActivityRuntimePermissionsBinding
import com.sanket.androidplayground2.misc.permissions.LocationPermissionActivity

class RuntimePermissionsActivity : AppCompatActivity() {

    private val binding: ActivityRuntimePermissionsBinding by lazy { ActivityRuntimePermissionsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLocationPermission.setOnClickListener { openActivity<LocationPermissionActivity>() }
    }
}