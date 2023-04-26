package com.botkova.magicfridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        supportActionBar?.title = "Friends"
    }
}