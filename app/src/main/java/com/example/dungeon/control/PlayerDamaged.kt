package com.example.dungeon.control

import androidx.annotation.NonNull
import com.example.dungeon.model.Player
import com.example.dungeon.monster.Monster
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.roundToInt


class PlayerDamaged(@NonNull private val monster: Monster,@NonNull private val player: Player) {

    /**@return возвращает урон сильной атаки*/
    fun getDamageStrong(): Int = when ((0..100).random()) {
        in 0..player.hitProbability -> getDamage(monster.resistanceStrong)
        else -> 0
    }

    /**@return возвращает урон быстрой атаки*/
    fun getDamageQuick(): Int = when ((0..100).random()) {
        in 0..player.hitProbability -> getDamage(monster.resistanceQuick)
        else -> 0
    }


    /**@return возвращает урон финта*/
    fun getDamageFeint(): Int = when ((0..100).random()) {
        in 0..player.hitProbability -> getDamage(monster.resistanceFeint)
        else -> 0
    }

    private fun getDamage(resistance: Double): Int = (player.damage / resistance * ThreadLocalRandom.current()
        .nextDouble(0.85, 1.0)).roundToInt()

}