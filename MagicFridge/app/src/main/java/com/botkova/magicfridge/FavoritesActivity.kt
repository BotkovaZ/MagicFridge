package com.botkova.magicfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.botkova.magicfridge.adapters.RecipesAdapter
import com.botkova.magicfridge.databinding.ActivityFavoritesBinding
import com.botkova.magicfridge.models.Recipe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoritesActivity : AppCompatActivity() {

    lateinit var favoritesBinding: ActivityFavoritesBinding

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference.child("Recipes")

    val recipesList = ArrayList<Recipe>()
    lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesBinding = ActivityFavoritesBinding.inflate(layoutInflater)
        val view = favoritesBinding.root
        setContentView(view)

        supportActionBar?.title = "Favorite recipes"

        favoritesBinding.textView4.text = "These are your favorite recipes:"

        retrieveDataFromDatabase()

        favoritesBinding.bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.ingredients -> {
                    val intent = Intent(this, IngredientsActivity::class.java)
                    startActivity(intent)
                    true
                }
//                R.id.favorite -> {
//                    val intent = Intent(this, FavoritesActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
                R.id.friends -> {
                    val intent = Intent(this, FriendsActivity::class.java)
                    startActivity(intent)
                    true
                }
//                R.id.settings -> {
//                    val intent = Intent(this, SettingsActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
            }
        }
    }

    fun retrieveDataFromDatabase() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recipesList.clear()

                for (eachrecipe in snapshot.children) {
                    val recipe = eachrecipe.getValue(Recipe::class.java)

                    if (recipe != null && recipe.favorite) {
                        recipesList.add(recipe)
                    }

                    if (recipesList.isEmpty()) {
                        favoritesBinding.textView4.text = "You don't have any favorite recipes yet."
                    }

                    recipesAdapter = RecipesAdapter(this@FavoritesActivity, recipesList)

                    favoritesBinding.recyclerView.layoutManager = LinearLayoutManager(this@FavoritesActivity)

                    favoritesBinding.recyclerView.adapter = recipesAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}