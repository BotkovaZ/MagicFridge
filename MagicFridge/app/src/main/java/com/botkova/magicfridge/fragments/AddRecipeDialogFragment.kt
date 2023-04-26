package com.botkova.magicfridge.fragments

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.botkova.magicfridge.R
import com.botkova.magicfridge.models.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddRecipeDialogFragment : DialogFragment() {

    lateinit var name : EditText
    lateinit var cookingTime : EditText
    lateinit var ingredients : EditText
    lateinit var cookingProcedure : EditText
    lateinit var saveRecipe : Button

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference : DatabaseReference = database.reference.child("Recipes")
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_add_recipe_dialog, container, false)

        name = view.findViewById(R.id.etNameShopping)
        cookingTime = view.findViewById(R.id.etCookingTime)
        ingredients = view.findViewById(R.id.etIngredients)
        cookingProcedure = view.findViewById(R.id.etCookingProcedure)
        saveRecipe = view.findViewById(R.id.btnSaveRecipe)

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        saveRecipe.setOnClickListener {
            addRecipeToDatabase()
            dismiss()
        }

        return view
    }

    fun addRecipeToDatabase() {
        val name : String = name.text.toString()
        val cookingTime : Int = cookingTime.text.toString().toInt()
        val ingredients : String = ingredients.text.toString()
        val cookingProcedure : String = cookingProcedure.text.toString()
        var uid : String = auth.currentUser?.uid.toString()

        val id : String = reference.push().key.toString()

        val recipe = Recipe(id, name, cookingTime, ingredients, cookingProcedure, false, uid)

        reference.child(id).setValue(recipe)
    }
}