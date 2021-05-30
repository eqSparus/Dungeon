package com.example.dungeon.monster

/**
 * @param name имя монстра
 * @param health здоровье монстра
 * @param idImageMonster картинка монстра
 * @param damageStrong сильная атака
 * @param damageQuick быстрая атака
 * @param damageDeception финт
 * @param hitStrong вероятность сильного удара
 * @param hitQuick вероятность быстрого удара
 * @param hitFeint вероятность финта
 * @param resistanceStrong сопротивляемость сильным ударам
 * @param resistanceQuick сопротивляемость быстрым ударам
 * @param resistanceFeint сопротивляемость финтам
 */
interface Monster {
    val name: String
    var health: Int
    val idImageMonster: Int

    val damageStrong: Int
    val damageQuick: Int
    val damageDeception: Int

    val hitStrong: Int
    val hitQuick: Int
    val hitFeint: Int

    val resistanceStrong: Double
    val resistanceQuick: Double
    val resistanceFeint: Double

    val textHelp: Int

    val ex:Int
}