package com.example.dungeon.monster

import com.example.dungeon.R


data class OneEye(
    override val name: String = "Выбрал вилку",
    override var health: Int = 80,
    override val idImageMonster: Int = R.drawable.one_eye,

    override val damageStrong: Int = 130,
    override val damageQuick: Int = 75,
    override val damageDeception: Int = 30,

    override val hitStrong: Int = 40,
    override val hitQuick: Int = 90,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 0.9,
    override val resistanceQuick: Double = 2.0,
    override val resistanceFeint: Double = 1.0,

    override val textHelp: Int = R.string.text_one_eye,
    override val ex: Int = 15
) : Monster
