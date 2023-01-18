package com.example.pokekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class Stats : AppCompatActivity() {
    private lateinit var pokeApiClient: PokeApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        val pokemonName: TextView = findViewById(R.id.nom_poke_stat)
        val pokemonAttaque: TextView = findViewById(R.id.statAttaque)
        val pokemonDefense: TextView = findViewById(R.id.statDefense)
        val pokemonHP: TextView = findViewById(R.id.statHP)
        val pokemonVitesse : TextView = findViewById(R.id.statVitesse)
        val pokemonImage: ImageView = findViewById(R.id.img_stat)

        val pokemonId = intent.getIntExtra("pokemon_id", 0)
        pokeApiClient = PokeApiClient()
        pokeApiClient.getPokemon(pokemonId) { pokemon ->
            if (pokemon != null) {
                //val pokemon = pokemonResponse.toPokemon()
                pokemonName.text = pokemon.name
                Glide.with(this)
                    .load(pokemon.sprites.front_default)
                    .into(pokemonImage)
                for (statname in pokemon.stats) {
                    if (statname.stat.name == "attack") {
                        //        Log.d("pokeApi","$pokemon.stats.stat")
                        pokemonAttaque.text = statname.base_stat.toString()
                    } else if (statname.stat.name == "defense") {
                        pokemonDefense.text = statname.base_stat.toString()

                    } else if (statname.stat.name == "speed") {
                        pokemonVitesse.text = statname.base_stat.toString()

                    } else if (statname.stat.name == "hp") {
                        pokemonHP.text = statname.base_stat.toString()
                    }
                }
            }
        }
    }

    //fun PokemonRemote.toPokemon(): Pokemon {
    //    val stats = this.stats.map { stat ->
    //        Stat(0, stat.baseStat, NamedApiResource(stat.stat.name), this.id)
    //    }
    //    return Pokemon(this.id, this.name, this.weight, this.height, Sprites(this.sprites.frontDefault), stats)
    //}
}