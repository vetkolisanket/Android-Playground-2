package com.sanket.androidplayground2.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.FragmentHolderActivity
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.Constants
import com.sanket.androidplayground2.commons.utils.openActivity
import kotlinx.android.synthetic.main.activity_coroutines.*

class CoroutinesExamplesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        initBtns()
    }

    private fun initBtns() {
        btnSingleNetworkCall.setOnClickListener { openActivity<SingleNetworkCallActivity>() }
        btnSeriesNetworkCall.setOnClickListener { openActivity<SeriesNetworkCallActivity>() }
        btnParallelNetworkCall.setOnClickListener { openActivity<ParallelNetworkCallActivity>() }
        btnRoomDatabase.setOnClickListener { openActivity<RoomDatabaseActivity>() }
        btnLongRunningTask.setOnClickListener { openActivity<LongRunningTaskActivity>() }
        btnSimpleCoroutines.setOnClickListener { openActivity<FragmentHolderActivity> {
            putExtra(Constants.FRAGMENT_TYPE, Constants.Fragments.SIMPLE_COROUTINES)
        } }
    }
}