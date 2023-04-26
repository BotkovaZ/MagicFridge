 package com.botkova.magicfridge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.botkova.magicfridge.R
import com.botkova.magicfridge.models.Ingredient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.core.UserData

 class AddIngredientDialogFragment : DialogFragment() {

     lateinit var name : EditText
     lateinit var save : Button

     private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
     private val reference : DatabaseReference = database.reference.child("Ingredients")
     private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_add_ingredient_dialog, container, false)

        name = view.findViewById(R.id.etIngredientName)
        save = view.findViewById(R.id.btnSaveIngredient)

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        save.setOnClickListener {
            addIngredientToDatabase()
            dialog!!.dismiss()
        }

        return view
    }

     fun addIngredientToDatabase() {
         val name : String = name.text.toString()

         val userId : String = auth.currentUser?.uid.toString()

         val id : String = reference.push().key.toString()

         val ingredient = Ingredient(id, name, userId)

         reference.child(id).setValue(ingredient)
     }
}