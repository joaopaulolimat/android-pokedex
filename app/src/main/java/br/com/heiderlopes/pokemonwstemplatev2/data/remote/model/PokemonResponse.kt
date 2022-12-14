package br.com.heiderlopes.pokemonwstemplatev2.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val number: String,
    @SerializedName("name") val name: String,
    @SerializedName("imageURL") val imageURL: String,
    @SerializedName("ps") var ps: Int,
    @SerializedName("attack") var attack: Int,
    @SerializedName("defense") var defense: Int,
    @SerializedName("velocity") var velocity: Int
)
