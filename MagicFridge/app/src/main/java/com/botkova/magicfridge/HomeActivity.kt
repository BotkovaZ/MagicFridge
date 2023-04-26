package com.botkova.magicfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.botkova.magicfridge.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var homeBinding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        val view = homeBinding.root
        setContentView(view)
        supportActionBar?.hide()
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        homeBinding.btnIngredients.setOnClickListener {
            val intent = Intent(this@HomeActivity, IngredientsActivity::class.java)
            startActivity(intent)
        }

        homeBinding.btnFavorites.setOnClickListener {
            val intent = Intent(this@HomeActivity, FavoritesActivity::class.java)
            startActivity(intent)
        }

        homeBinding.btnShopping.setOnClickListener {
            val intent = Intent(this@HomeActivity, ShoppingActivity::class.java)
            startActivity(intent)
        }

        homeBinding.btnFriends.setOnClickListener {
            val intent = Intent(this@HomeActivity, FriendsActivity::class.java)
            startActivity(intent)
        }

        homeBinding.btnSettings.setOnClickListener {
            val intent = Intent(this@HomeActivity, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}