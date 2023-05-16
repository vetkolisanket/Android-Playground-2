package com.sanket.androidplayground2.misc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.databinding.ActivityAddCalendarEventBinding
import java.util.Calendar

class AddCalendarEventActivity : AppCompatActivity() {

    private val binding: ActivityAddCalendarEventBinding by lazy { ActivityAddCalendarEventBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_calendar_event)
        binding.btnSetEvent.setOnClickListener {
            setEvent()
        }
    }

    private fun setEvent() {
        val startMillis = Calendar.getInstance()
        startMillis.add(Calendar.MINUTE, 15)
        val endMillis = Calendar.getInstance()
        endMillis.add(Calendar.MINUTE, 30)

        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis.timeInMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis.timeInMillis)
            .putExtra(CalendarContract.Events.TITLE, "Test title")
        startActivity(intent)
    }
}