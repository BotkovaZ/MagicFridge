package com.botkova.magicfridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.botkova.magicfridge.databinding.ActivityRegisterBinding
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var registerBinding : ActivityRegisterBinding

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        registerBinding.btnSave.setOnClickListener {
            val email = registerBinding.etEmail.text.toString()
            val password = registerBinding.etPassw.text.toString()

            registerWithFirebase(email, password)
        }
    }

    fun registerWithFirebase(email : String, password : String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Your account has been created.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}