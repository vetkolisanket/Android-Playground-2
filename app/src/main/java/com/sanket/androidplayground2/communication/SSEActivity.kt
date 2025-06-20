package com.sanket.androidplayground2.communication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.sanket.androidplayground2.commons.utils.TAG
import com.sanket.androidplayground2.databinding.ActivitySseactivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.io.IOException
import java.util.concurrent.TimeUnit

class SSEActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySseactivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val eventSourceListener = object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                super.onOpen(eventSource, response)
                Log.d(TAG, "onOpen: $eventSource")
            }

            override fun onClosed(eventSource: EventSource) {
                super.onClosed(eventSource)
                Log.d(TAG, "onClosed: $eventSource")
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                super.onFailure(eventSource, t, response)
                Log.d(
                    TAG,
                    "onFailure: eventsource: ${eventSource.TAG}, response: $response, message: ${t?.message}"
                )
            }

            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                Log.d(TAG, "onEvent: $data")
            }
        }

        val client = OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()

        val request = Request.Builder()
            .url("https://test-sse-backend.herokuapp.com/events")
            .header("Accept", "application/json; q=0.5")
            .addHeader("Accept", "text/event-stream")
            .build()

        EventSources.createFactory(client)
            .newEventSource(request = request, listener = eventSourceListener)

        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) {
                client.newCall(request).enqueue(responseCallback = object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e(TAG, "API Call Failure ${e.localizedMessage}")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.d(TAG, "APi Call Success ${response.body.toString()}")
                    }
                })
            }
        }
    }
}