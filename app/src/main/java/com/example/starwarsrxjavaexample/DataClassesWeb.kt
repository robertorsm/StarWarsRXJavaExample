package com.example.starwarsrxjavaexample

import com.google.gson.annotations.SerializedName



data class FilmeResult(val results : List<Film>)

data class Film(val title:String,
                 @SerializedName("episode_id")
                 val episode_id:Int,
                 @SerializedName("characters")
                 val personUrls :List<String>)

data class Person (val name : String, val gender:String)
