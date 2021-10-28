package com.sanket.androidplayground2.commons.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

inline fun <reified T : Activity> Context.openActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context).load(imageUrl).into(this)
}

fun ViewGroup.inflate(layoutId: Int): View =
    LayoutInflater.from(this.context).inflate(layoutId, this, false)