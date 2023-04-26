package com.botkova.magicfridge.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.botkova.magicfridge.models.Ingredient
import com.botkova.magicfridge.RecipesActivity
import com.botkova.magicfridge.databinding.IngredientItemBinding

class IngredientsAdapter(var context : Context, var ingredientsList : ArrayList<Ingredient>) : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    inner class IngredientsViewHolder(val adapterBinding: IngredientItemBinding) : RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding = IngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.adapterBinding.tvName.text = ingredientsList[position].ingredientName

        holder.adapterBinding.linearLayout.setOnClickListener {
            val intent = Intent(context, RecipesActivity::class.java)
            intent.putExtra("id", ingredientsList[position].ingredientId)
            intent.putExtra("ingredientName", ingredientsList[position].ingredientName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun getIngredientId(position: Int) : String {
        return ingredientsList[position].ingredientId
    }
}