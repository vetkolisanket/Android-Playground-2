package com.sanket.androidplayground2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.androidplayground2.appcomponents.AppComponentsActivity
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.communication.CommunicationActivity
import com.sanket.androidplayground2.compose.ComposeActivity
import com.sanket.androidplayground2.coroutines.CoroutinesExamplesActivity
import com.sanket.androidplayground2.databinding.ActivityMainBinding
import com.sanket.androidplayground2.flow.FlowActivity
import com.sanket.androidplayground2.hilt.HiltActivity
import com.sanket.androidplayground2.misc.MiscActivity
import com.sanket.androidplayground2.third_party.LibrariesActivity
import com.sanket.androidplayground2.widgets.WidgetsActivity
import com.sanket.androidplayground2.widgets.internet_observer.InternetObserverActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.apply {
            btnSpeechToText.setOnClickListener { startActivity(SpeechToTextActivity.newIntent(this@MainActivity)) }
            btnSearch.setOnClickListener { startActivity(SearchActivity.newIntent(this@MainActivity)) }
            btnAppComponents.setOnClickListener { startActivity(AppComponentsActivity.newIntent(this@MainActivity)) }
            btnHilt.setOnClickListener { openActivity<HiltActivity>() }
            btnCoroutines.setOnClickListener { openActivity<CoroutinesExamplesActivity>() }
            btnWidget.setOnClickListener { openActivity<WidgetsActivity>() }
            btnLibraries.setOnClickListener { openActivity<LibrariesActivity>() }
            btnCompose.setOnClickListener { openActivity<ComposeActivity>() }
            btnInternetObserver.setOnClickListener { openActivity<InternetObserverActivity>() }
            btnCommunication.setOnClickListener { openActivity<CommunicationActivity>() }
            btnMisc.setOnClickListener { openActivity<MiscActivity>() }
            btnFlows.setOnClickListener { openActivity<FlowActivity>() }
        }
    }


}
