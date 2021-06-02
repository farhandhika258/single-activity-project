package com.example.myapplication.ui

import android.widget.Toast
import com.example.myapplication.di.SingleActivity
import com.example.myapplication.domain.Announcer
import javax.inject.Inject

@SingleActivity
class AnnouncerImpl@Inject constructor(
        private val mainActivity: MainActivity
): Announcer {

    override fun announce(text: String) {
        Toast.makeText(mainActivity, text, Toast.LENGTH_LONG).show()
    }
}