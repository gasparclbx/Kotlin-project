package com.example.pokekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var pokeApiClient: PokeApiClient
    private lateinit var pokemonName: TextView
    private lateinit var pokemonImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonName = findViewById(R.id.pokemon_name)
        pokemonImage = findViewById(R.id.image_pokemon)

        pokeApiClient = PokeApiClient()
        val pokemonId = intent.getIntExtra("pokemonId", 25)
        pokeApiClient.getPokemon(pokemonId) { pokemon ->
            if (pokemon != null) {
                pokemonName.text = pokemon.name
                Glide.with(this).load(pokemon.sprites.front_default).into(pokemonImage)
            } else {
                Toast.makeText(this, "Error while fetching pokemon data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}