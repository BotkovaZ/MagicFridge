package com.example.magicfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.magicfridge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding
    lateinit var name : EditText
    lateinit var password : EditText
    lateinit var login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)

        login.setOnClickListener {
            var name : String = name.text.toString()
            var password : String = password.text.toString()

            var intent = Intent(this@MainActivity, RegisterActivity::class.java)

        }
    }
}