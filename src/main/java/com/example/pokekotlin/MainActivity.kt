package com.example.pokekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var pokeApiClient: PokeApiClient
    private lateinit var pokemonRecyclerView: RecyclerView
    private lateinit var adapter: PokemonAdapter

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId)
        {
            R.id.nav_Pokedex ->
            {
                val intent = Intent(Intent.ACTION_WEB_SEARCH)
                startActivity(intent)
            }
            else -> Toast.makeText(this, item.title, Toast.LENGTH_LONG).show()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PokemonAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.pokemon_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        pokeApiClient = PokeApiClient()
        for (i in 0..151) {
            pokeApiClient.getPokemon(i) { pokemon ->
                if (pokemon != null) {
                    adapter.addPokemon(pokemon)
                }
            }
        }

    }
}