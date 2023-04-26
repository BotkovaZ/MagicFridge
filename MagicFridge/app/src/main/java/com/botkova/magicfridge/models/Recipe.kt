package com.botkova.magicfridge.models

data class Recipe(
    val recipeId : String = "",
    val recipeName : String = "",
    val cookingTime: Int = 0,
    val ingredients : String = "" /*ArrayList<String> = ArrayList()*/,
    val cookingProcedure : String = "",
    var favorite : Boolean = false,
    val userId : String = ""
) {}