package com.botkova.magicfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.botkova.magicfridge.databinding.ActivityRecipeBinding
import com.botkova.magicfridge.fragments.UsedFragmentDialog
import com.botkova.magicfridge.models.Recipe
import com.google.firebase.database.*


class RecipeActivity : AppCompatActivity() {

    private lateinit var recipeBinding : ActivityRecipeBinding

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()

    private val recipeId = intent?.getStringExtra("recipeId").toString()
    private val ingredientId = intent?.getStringExtra("ingredientId").toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeBinding = ActivityRecipeBinding.inflate(layoutInflater)
        val view = recipeBinding.root
        setContentView(view)

        setData()
    }

    override fun onBackPressed() {
//        super.onBackPressed()

        val fragmentManager : FragmentManager = supportFragmentManager
        val usedDialogFragment = UsedFragmentDialog()

        usedDialogFragment.show(fragmentManager, "UsedDialogFragment")
    }

    private fun setData() {
        supportActionBar?.title = intent.getStringExtra("recipeName")

        recipeBinding.tvName.text = intent.getStringExtra("recipeName")
        recipeBinding.tvCookingTime.text = "${intent.getIntExtra("cookingTime", -1).toString()} minutes"
        recipeBinding.tvIngredients.text = intent.getStringExtra("ingredients")
        recipeBinding.tvCookingProcedure.text = intent.getStringExtra("cookingProcedure")
    }
}
