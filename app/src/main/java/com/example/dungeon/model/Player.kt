package com.example.dungeon.model

import androidx.annotation.NonNull
import java.io.Serializable

data class Player(
    @NonNull val name: String,
    var health: Int = 100,
    var potions: Int = 0,
    var gold: Int = 0,

    var damage: Int = 60,
    var evasion: Int = 30,
    var hitProbability: Int = 50,

    var experience: Int = 0,
    var level: Int = 1,
    var point: Int = 0,

    var lvlArena: Int = 0
) : Serializable