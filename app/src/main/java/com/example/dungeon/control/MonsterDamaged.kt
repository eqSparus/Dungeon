package com.example.dungeon.control

import androidx.annotation.NonNull
import com.example.dungeon.model.Player
import com.example.dungeon.monster.Monster
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.roundToInt

class MonsterDamaged(@NonNull private val monster: Monster,@NonNull private val player: Player) {

    /**@return возвращает урон монстра*/
    fun getDamageMonster(): Int = if ((0..100).random() > player.evasion) 0 else getTypeAttack()

    /**@return возвращает урона типа атки*/
    private fun getTypeAttack(): Int = when ((0..100).random()) {
        in 0..monster.hitStrong -> getDamage(monster.damageStrong)
        in monster.hitStrong..monster.hitQuick -> getDamage(monster.damageQuick)
        in monster.hitQuick..monster.hitFeint -> getDamage(monster.damageDeception)
        else -> 0
    }

    private fun getDamage(damage:Int): Int =
        (damage / 1.5 * ThreadLocalRandom.current().nextDouble(0.85, 1.0)).roundToInt()
}