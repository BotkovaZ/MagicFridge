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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddShoppingDialog : DialogFragment() {

//    lateinit var dialogBinding : FragmentAddShoppingDialogBinding

    lateinit var name : EditText
    lateinit var save : Button

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference : DatabaseReference = database.reference.child("ShoppingList")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_add_shopping_dialog, container, false)

        name = view.findViewById(R.id.etNameShopping)
        save = view.findViewById(R.id.btnSave)

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        save.setOnClickListener {
            addShoppingToDatabase()
        }

        dialog!!.dismiss()

//        save.setOnClickListener {
//            addShoppingToDatabase()
//            dialog!!.dismiss()
//        }

        return view
    }

    fun addShoppingToDatabase() {
        val name : String = name.text.toString()

        val id : String = reference.push().key.toString()

        val shoppingItem = Ingredient(id, name)

        reference.child(id).setValue(shoppingItem)
    }
}