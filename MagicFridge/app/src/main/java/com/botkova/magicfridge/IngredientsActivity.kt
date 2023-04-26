package com.botkova.magicfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.botkova.magicfridge.adapters.IngredientsAdapter
import com.botkova.magicfridge.databinding.ActivityIngredientsBinding
import com.botkova.magicfridge.fragments.AddIngredientDialogFragment
import com.botkova.magicfridge.models.Ingredient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class IngredientsActivity : AppCompatActivity() {

    lateinit var ingredientsBinding : ActivityIngredientsBinding

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference : DatabaseReference = database.reference.child("Ingredients")
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    val ingredientsList = ArrayList<Ingredient>()
    lateinit var ingredientsAdapter: IngredientsAdapter
    val uid : String = auth.currentUser?.uid.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ingredientsBinding = ActivityIngredientsBinding.inflate(layoutInflater)
        val view = ingredientsBinding.root
        setContentView(view)

        supportActionBar?.title = "Ingredients"

        ingredientsBinding.btnAddIngredient.setOnClickListener {
            val fragmentManager : FragmentManager = supportFragmentManager
            val addIngredientDialogFragment = AddIngredientDialogFragment()

            addIngredientDialogFragment.show(fragmentManager, "AddIngredientFragment")
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
                val id = ingredientsAdapter.getIngredientId(viewHolder.adapterPosition)

                reference.child(id).removeValue()

                Toast.makeText(applicationContext, "Ingredient was deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(ingredientsBinding.recyclerView)

        retrieveDataFromDatabase()

        ingredientsBinding.bottomNav.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.friends -> {
                    val intent = Intent(this, FriendsActivity::class.java)
                    startActivity(intent)
                }
                R.id.shoppingList -> {
                    val intent = Intent(this, ShoppingActivity::class.java)
                    startActivity(intent)
                }
//                R.id.settings -> {
//                    val intent = Intent(this, SettingsActivity::class.java)
//                    startActivity(intent)
//                }
            }
        }
    }

    private fun retrieveDataFromDatabase() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ingredientsList.clear()

                for (eachingredient in snapshot.children) {
                    val ingredient = eachingredient.getValue(Ingredient::class.java)

                    if (ingredient?.userId == uid) {
                        ingredientsList.add(ingredient)
                    }

                    ingredientsAdapter = IngredientsAdapter(this@IngredientsActivity, ingredientsList)

                    ingredientsBinding.recyclerView.layoutManager = LinearLayoutManager(this@IngredientsActivity)

                    ingredientsBinding.recyclerView.adapter = ingredientsAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}