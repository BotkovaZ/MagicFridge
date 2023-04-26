package com.botkova.magicfridge.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.botkova.magicfridge.models.Recipe
import com.botkova.magicfridge.RecipeActivity
import com.botkova.magicfridge.databinding.RecipeItemBinding

class RecipesAdapter(var context: Context, var recipesList : ArrayList<Recipe>) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(val adapterBinding : RecipeItemBinding) : RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.adapterBinding.tvRecipeName.text = recipesList[position].recipeName
        holder.adapterBinding.tvCookingTime.text = "${recipesList[position].cookingTime} minutes"
        holder.adapterBinding.tvIngredients.text = recipesList[position].ingredients

//        holder.adapterBinding.btnFavorite.setOnClickListener {
//            recipesList[position].favorite = true
//        }

        holder.adapterBinding.cardView.setOnClickListener {
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("recipeId", recipesList[position].recipeId)
            intent.putExtra("recipeName", recipesList[position].recipeName)
            intent.putExtra("ingredients", recipesList[position].ingredients)
            intent.putExtra("cookingTime", recipesList[position].cookingTime)
            intent.putExtra("cookingProcedure", recipesList[position].cookingProcedure)
            intent.putExtra("ingredientId", intent.getStringExtra("ingredientId"))
            intent.putExtra("ingredientName", intent.getStringExtra("ingredientName"))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    fun getRecipeId(position: Int) : String {
        return recipesList[position].recipeId
    }
}