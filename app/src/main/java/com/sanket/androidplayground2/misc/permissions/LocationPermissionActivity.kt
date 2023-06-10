package com.sanket.androidplayground2.misc.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.databinding.ActivityLocationPermissionBinding

class LocationPermissionActivity : AppCompatActivity() {

    private val binding: ActivityLocationPermissionBinding by lazy {
        ActivityLocationPermissionBinding.inflate(
            layoutInflater
        )
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission granted, fetch location", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(this, "Fetch and render location", Toast.LENGTH_SHORT).show()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showExplainer()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun showExplainer() {
        Toast.makeText(this, "Show location explainer", Toast.LENGTH_SHORT).show()
    }
}