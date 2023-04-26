package com.botkova.magicfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.botkova.magicfridge.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding : ActivityMainBinding

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        supportActionBar?.hide()

        mainBinding.btnLogin.setOnClickListener {
            val email = mainBinding.etEmailLogin.text.toString()
            val password = mainBinding.etPassword.text.toString()

            signinWithFirebase(email, password)
        }

        mainBinding.btnRegister.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        mainBinding.btnForgotPassword.setOnClickListener {
            val intent = Intent(this@MainActivity, ForgotActivity::class.java)
            startActivity(intent)
        }
    }

    fun signinWithFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//
//        val user = auth.currentUser
//
//        if (user != null) {
//            val intent = Intent(this@MainActivity, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
}


//    lateinit var mainBinding : ActivityMainBinding
//
//    val auth : FirebaseAuth = FirebaseAuth.getInstance()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mainBinding = ActivityMainBinding.inflate(layoutInflater)
//        val view = mainBinding.root
//        setContentView(view)
//
//        mainBinding.btnLogin.setOnClickListener {
//            val email = mainBinding.etEmailLogin.text.toString()
//            val password = mainBinding.etPassword.text.toString()
//
//            signinWithFirebase(email, password)
//        }
//
//        mainBinding.btnRegister.setOnClickListener {
//            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//
//        mainBinding.btnForgotPassword.setOnClickListener {
//            val intent = Intent(this@MainActivity, ForgotActivity::class.java)
//            startActivity(intent)
//        }
//    }

//    fun signinWithFirebase(email: String, password: String) {
//        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
//            if (task.isSuccessful) {
//                val intent = Intent(this@MainActivity, HomeActivity::class.java)
//                startActivity(intent)
//                finish()
//            } else {
//                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        val user = auth.currentUser
//
//        if (user != null) {
//            val intent = Intent(this@MainActivity, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
//}