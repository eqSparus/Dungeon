package com.example.dungeon.control

import androidx.annotation.NonNull
import com.example.dungeon.model.Player
import com.example.dungeon.monster.Monster

class Battle(@NonNull private val player: Player,@NonNull private val monster: Monster) {

    /**Переменная для получения урона игрока*/
    private val playerDamaged = PlayerDamaged(monster, player)
    private val monsterDamage = MonsterDamaged(monster, player)

    /**
     *@param hit номер удара
     *@return Возвращает строку вида удара
     */
    fun getDamageReception(@NonNull hit: Int): String {
        var damaged = 0
        when (hit) {
            1 -> {
                damaged = playerDamaged.getDamageStrong()
                monster.health -= damaged
            }
            2 -> {
                damaged = playerDamaged.getDamageQuick()
                monster.health -= damaged
            }
            3 -> {
                damaged = playerDamaged.getDamageFeint()
                monster.health -= damaged
            }
        }
        return if (damaged == 0) "${player.name}  промахнулся"
        else "${player.name}  нанес $damaged урона"
    }

    /**@return Возвращает строку лечения*/
    fun drinkPotions(): String = if (player.potions != 0) {
        player.health += 50
        player.potions -= 1
        if (player.health > 100) player.health = 100
        "${player.name} востановил 50 здоровья"
    } else {
        "Кончились бутылки"
    }


    /**@return Возвращает строку удара монстра*/
    fun monsterReception(): String {
        val damage = monsterDamage.getDamage()
        return if (damage == 0){
            "${monster.name} промахнулся"
        } else {
            player.health -= damage
            "${monster.name} нанес $damage урона"
        }
    }

    /**@return Проверка на победу игрока*/
    fun isVictoryPlayer(): Boolean = monster.health <= 0

    /**@return Проверка на победу монстра*/
    fun isVictoryMonster(): Boolean = player.health <= 0
}