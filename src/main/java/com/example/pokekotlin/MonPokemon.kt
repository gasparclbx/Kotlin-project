package com.example.pokekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.bumptech.glide.Glide

class MonPokemon : AppCompatActivity() {
    private lateinit var pokeApiClient: PokeApiClient
    val pokemonData = mutableListOf<String>()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_Pokedex -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_Equipe -> {
                val intent = Intent(this, Equipe::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mon_pokemon)
        val pokemonId = intent.getIntExtra("pokemon_id", 0)
        val pokemonNum = intent.getIntExtra("pokemon_num", 0)
        pokeApiClient = PokeApiClient()

        //affichage de la carte
        val pokemonName: TextView = findViewById(R.id.nom_poke_stat)
        val newPokemonName: EditText = findViewById(R.id.nom_poke_modif)
        val buttonModif: Button = findViewById(R.id.modif_nom_poke)
        val pokemonAttaque: TextView = findViewById(R.id.statAttaque)
        val pokemonDefense: TextView = findViewById(R.id.statDefense)
        val pokemonHP: TextView = findViewById(R.id.statHP)
        val pokemonVitesse: TextView = findViewById(R.id.statVitesse)
        val pokemonImage: ImageView = findViewById(R.id.img_stat)

        buttonModif.setOnClickListener {
            val newName = newPokemonName.text.toString()
            pokemonName.text = newName
            MyApplication.userTeam[pokemonNum][1] = newName
            // mettre à jour le nom dans votre liste
            (application as MyApplication).saveUserTeam()
        }

        pokeApiClient.getPokemon(pokemonId) { pokemon ->
            if (pokemon != null) {
                //val pokemon = pokemonResponse.toPokemon()
                pokemonName.text =  MyApplication.userTeam[pokemonNum][1]
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
}
