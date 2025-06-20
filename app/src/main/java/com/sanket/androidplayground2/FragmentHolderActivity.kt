package com.sanket.androidplayground2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sanket.androidplayground2.coroutines.SimpleCoroutinesFragment
import com.sanket.androidplayground2.databinding.ActivityFragmentHolderBinding

class FragmentHolderActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFragmentHolderBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, getFragmentBasedOnIntent())
            .commit()
    }

    fun getFragmentBasedOnIntent(): Fragment {
        return SimpleCoroutinesFragment.newInstance()
    }
}