package com.example.dungeon.config

import android.content.Context
import androidx.annotation.NonNull
import com.example.dungeon.model.Player
import java.io.File


/**
 * Класс для сохранения данных для доски почета
 */
class EndGamePlayer(@NonNull private val context: Context) {

    private val file = File(context.filesDir.absolutePath + NameFile.FILE_SAVE_BETTER_PLAYER)

    /**
     * @return возвращает массив пар значения имени и кол-во золота игрока
     */
    fun getBetterPlayer(): ArrayList<Pair<String, Int>> {

        if (!file.exists()) file.createNewFile()

        val betterPlayer = arrayListOf<Pair<String, Int>>()
        file.forEachLine {
            val slt = it.split(",")
            betterPlayer.add(slt[0] to slt[1].toInt())
        }
        return betterPlayer
    }

    /**
     *@param score золото игрока
     *@return индекс заменяемого игрока
     */
    private fun getIndexNewPlayer(@NonNull score: Int): Int {
        val betterPlayer = getBetterPlayer()
        var ind = -1
        betterPlayer.forEachIndexed { index, pair ->
            if (pair.second < score)
                ind = index
        }
        return ++ind
    }

    /**
     *@param player игрока которого нужно сохранить
     */
    fun checkBetterPlayer(@NonNull player: Player) {
        val name = player.name
        val score = player.gold + player.level * 3 + player.lvlArena * 2

        val ind = getIndexNewPlayer(score)
        val mass = getBetterPlayer()
        mass.add(ind, name to score)

        if (mass.size > 3) mass.removeFirst()

        file.writeText("")
        mass.forEach {
            file.appendText("${it.first},${it.second}\n")
        }
    }

}