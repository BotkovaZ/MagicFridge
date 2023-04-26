package com.botkova.magicfridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.botkova.magicfridge.adapters.ShoppingAdapter
import com.botkova.magicfridge.databinding.ActivityShoppingBinding
import com.botkova.magicfridge.fragments.AddShoppingDialog
import com.botkova.magicfridge.models.Ingredient
import com.google.firebase.database.*

class ShoppingActivity : AppCompatActivity() {

    lateinit var shoppingBinding: ActivityShoppingBinding

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference : DatabaseReference = database.reference.child("ShoppingList")

    val shoppingIngredients = ArrayList<Ingredient>()
    lateinit var shoppingAdapter: ShoppingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoppingBinding = ActivityShoppingBinding.inflate(layoutInflater)
        val view = shoppingBinding.root
        setContentView(view)

        supportActionBar?.title = "Shopping list"

        shoppingBinding.btnAdd.setOnClickListener {
            val fragmentManager : FragmentManager = supportFragmentManager
            val addShoppingDialog = AddShoppingDialog()

            addShoppingDialog.show(fragmentManager, "AddSHoppingDialog")
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
                val id = shoppingAdapter.getIngredientId(viewHolder.adapterPosition)

                reference.child(id).removeValue()

                Toast.makeText(applicationContext, "Ingredient was deleted from the shopping list", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(shoppingBinding.recyclerView)

        retrieveDataFromDatabase()
    }

    private fun retrieveDataFromDatabase() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                shoppingIngredients.clear()

                for (eachingredient in snapshot.children) {
                    val ingredient = eachingredient.getValue(Ingredient::class.java)

                    if (ingredient != null) {
                        shoppingIngredients.add(ingredient)
                    }

                    shoppingAdapter = ShoppingAdapter(this@ShoppingActivity, shoppingIngredients)

                    shoppingBinding.recyclerView.layoutManager = LinearLayoutManager(this@ShoppingActivity)

                    shoppingBinding.recyclerView.adapter = shoppingAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}