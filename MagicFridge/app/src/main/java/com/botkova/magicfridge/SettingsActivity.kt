package com.botkova.magicfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.botkova.magicfridge.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {

    lateinit var settingsBinding : ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = settingsBinding.root
        setContentView(view)

        supportActionBar?.title = "Settings"

        settingsBinding.theme.setOnCheckedChangeListener { buttonVIew, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            }
        }

        settingsBinding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@SettingsActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        settingsBinding.bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
//                    true
                }
                R.id.ingredients -> {
                    val intent = Intent(this, IngredientsActivity::class.java)
                    startActivity(intent)
//                    true
                }
//                R.id.favorite -> {
//                    val intent = Intent(this, FavoritesActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
                R.id.friends -> {
                    val intent = Intent(this, FriendsActivity::class.java)
                    startActivity(intent)
//                    true
                }
//                R.id.settings -> {
//                    val intent = Intent(this, SettingsActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
            }
        }
    }
}