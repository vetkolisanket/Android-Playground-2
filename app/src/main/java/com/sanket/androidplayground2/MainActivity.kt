package com.sanket.androidplayground2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.androidplayground2.appcomponents.AppComponentsActivity
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.communication.CommunicationActivity
import com.sanket.androidplayground2.compose.ComposeActivity
import com.sanket.androidplayground2.coroutines.CoroutinesExamplesActivity
import com.sanket.androidplayground2.hilt.HiltActivity
import com.sanket.androidplayground2.third_party.LibrariesActivity
import com.sanket.androidplayground2.widgets.WidgetsActivity
import com.sanket.androidplayground2.widgets.internet_observer.InternetObserverActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        btnSpeechToText.setOnClickListener { startActivity(SpeechToTextActivity.newIntent(this)) }
        btnSearch.setOnClickListener { startActivity(SearchActivity.newIntent(this)) }
        btnAppComponents.setOnClickListener { startActivity(AppComponentsActivity.newIntent(this)) }
        btnHilt.setOnClickListener { openActivity<HiltActivity>() }
        btnCoroutines.setOnClickListener { openActivity<CoroutinesExamplesActivity>() }
        btnWidget.setOnClickListener { openActivity<WidgetsActivity>() }
        btnLibraries.setOnClickListener { openActivity<LibrariesActivity>() }
        btnCompose.setOnClickListener { openActivity<ComposeActivity>() }
        btnInternetObserver.setOnClickListener { openActivity<InternetObserverActivity>() }
        btnCommunication.setOnClickListener { openActivity<CommunicationActivity> {  } }
    }


}
