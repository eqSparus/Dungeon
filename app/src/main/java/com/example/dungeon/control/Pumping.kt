package com.example.dungeon.control

import androidx.annotation.NonNull
import com.example.dungeon.model.Player

class Pumping(@NonNull private val player: Player) {

    fun setExperience(@NonNull ex: Int) {
        player.experience += ex
        if (player.experience >= 80) {
            player.experience -= 80
            player.point++
            player.level++
        }
    }

    fun setDamage() {
        if (player.point != 0 && player.damage < 100) {
            player.damage += 5
            player.point--
        }
    }

    fun setEvasion() {
        if (player.point != 0 && player.evasion < 60) {
            player.evasion += 5
            player.point--
        }
    }

    fun setHitProbability() {
        if (player.point != 0 && player.hitProbability < 70) {
            player.hitProbability += 5
            player.point--
        }
    }

}