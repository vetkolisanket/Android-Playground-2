package com.sanket.androidplayground2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sanket.androidplayground2.coroutines.SimpleCoroutinesFragment

class FragmentHolderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_holder)
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