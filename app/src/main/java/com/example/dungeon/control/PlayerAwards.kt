package com.example.dungeon.control

import androidx.annotation.NonNull
import com.example.dungeon.model.Player

class PlayerAwards(@NonNull private val player: Player) {

    /**@return Возвращает строку сокровища*/
    fun getTreasure(): String = when (val value = (0..100).random()) {
        in 0..30 -> {
            player.potions += 1
            "${player.name} получает 1 зелье"
        }
        in 30..100 -> {
            var gold = value
            if (player.lvlArena > 21) gold += value
            player.gold += gold
            "${player.name} получает $gold золота"
        }
        else -> "Что то пошло не так"
    }
}