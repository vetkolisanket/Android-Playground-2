package com.sanket.androidplayground2.commons.utils

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide

inline fun <reified T : Activity> Context.openActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}

inline fun <reified T: Service> Context.startService() {
    startService(Intent(this, T::class.java))
}

@RequiresApi(Build.VERSION_CODES.O)
inline fun <reified T: Service> Context.startForegroundService() {
    startForegroundService(Intent(this, T::class.java))
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context).load(imageUrl).into(this)
}

fun ViewGroup.inflate(layoutId: Int): View =
    LayoutInflater.from(this.context).inflate(layoutId, this, false)

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }