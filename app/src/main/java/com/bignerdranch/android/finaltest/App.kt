package com.bignerdranch.android.finaltest

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this;
    }
}