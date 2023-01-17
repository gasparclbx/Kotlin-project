package com.example.pokekotlin

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    @Embedded val sprites: Sprites,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    ) val stats: List<Stat>
)

data class Sprites(
    val front_default: String
)

@Entity(tableName = "stats")
data class Stat(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val baseStat: Int,
    val name: NamedApiResource,
    val pokemonId: Int
)

data class NamedApiResource(
    val name: String
)
