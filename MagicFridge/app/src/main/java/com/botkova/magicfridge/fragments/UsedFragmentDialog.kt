package com.botkova.magicfridge.fragments

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.botkova.magicfridge.R
import com.botkova.magicfridge.RecipeActivity
import com.botkova.magicfridge.RecipesActivity
import com.botkova.magicfridge.databinding.FragmentUsedDialogBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UsedFragmentDialog : DialogFragment() {

    lateinit var usedDialogBinding : FragmentUsedDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usedDialogBinding.btnYes.setOnClickListener {

        }

        usedDialogBinding.btnNo.setOnClickListener {
//            val intent = Intent(RecipeActivity, RecipesActivity::class.java)
//            startActivity(intent)
        }

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_used_dialog, container, false)
    }
}