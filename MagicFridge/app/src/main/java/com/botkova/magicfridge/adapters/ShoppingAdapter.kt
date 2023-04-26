package com.botkova.magicfridge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.botkova.magicfridge.databinding.IngredientItemBinding
import com.botkova.magicfridge.databinding.ShoppingItemBinding
import com.botkova.magicfridge.models.Ingredient

class ShoppingAdapter(val context: Context, var shoppingList: ArrayList<Ingredient>) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(val adapterBinding: ShoppingItemBinding) : RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context))

        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ShoppingViewHolder, position : Int) {
        holder.adapterBinding.tvName.text = shoppingList[position].ingredientName
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    fun getIngredientId(position : Int) : String {
        return shoppingList[position].ingredientId
    }
}