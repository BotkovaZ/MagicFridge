package com.botkova.magicfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.botkova.magicfridge.adapters.RecipesAdapter
import com.botkova.magicfridge.databinding.ActivityRecipesBinding
import com.botkova.magicfridge.fragments.AddRecipeDialogFragment
import com.botkova.magicfridge.models.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RecipesActivity : AppCompatActivity() {

    lateinit var recipesBinding : ActivityRecipesBinding

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference.child("Recipes")
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    val uid = auth.currentUser?.uid
    val recipesList = ArrayList<Recipe>()
    lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesBinding = ActivityRecipesBinding.inflate(layoutInflater)
        val view = recipesBinding.root
        setContentView(view)

        val ingredientName = intent.getStringExtra("ingredientName")

        supportActionBar?.title = ingredientName

        recipesBinding.textView4.text = "You can cook some nice meals:"

        recipesBinding.btnAddRecipe.setOnClickListener {
            val fragmentManager : FragmentManager = supportFragmentManager
            val addRecipeDialogFragment = AddRecipeDialogFragment()

//            addRecipeDialogFragment.isCancelable = false

            addRecipeDialogFragment.show(fragmentManager, "AddRecipeFragment")
        }



        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = recipesAdapter.getRecipeId(viewHolder.adapterPosition)

                reference.child(id).removeValue()

                Toast.makeText(applicationContext, "Recipe was removed", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recipesBinding.recyclerView)

        retrieveDataFromDatabase()

        recipesBinding.bottomNav.setOnNavigationItemReselectedListener {
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

    private fun retrieveDataFromDatabase() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recipesList.clear()

                for (eachrecipe in snapshot.children) {
                    val recipe = eachrecipe.getValue(Recipe::class.java)
                    val ingredientName : String = intent?.getStringExtra("ingredientName").toString()

//                    if ((recipe != null) && recipe.ingredients.contains(ingredientName)) {
//                        recipesList.add(recipe)
//                    }

                    if (recipe?.ingredients?.contains(ingredientName) == true && recipe.userId == uid) {
                        recipesList.add(recipe)
                    }

//                    if (recipe != null && recipe.ingredients.contains(ingredientName)) {
//                        recipesList.add(recipe)
//                    }

                    if (recipesList.isEmpty()) {
                        recipesBinding.textView4.text = "You don\'t have any recipes with ${ingredientName} yet. Do you want to add some?"
                    }

                    recipesAdapter = RecipesAdapter(this@RecipesActivity, recipesList)

                    recipesBinding.recyclerView.layoutManager = LinearLayoutManager(this@RecipesActivity)

                    recipesBinding.recyclerView.adapter = recipesAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}