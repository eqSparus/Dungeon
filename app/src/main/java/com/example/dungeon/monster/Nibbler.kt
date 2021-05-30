package com.example.dungeon.monster

import com.example.dungeon.R

data class Nibbler(
    override val name: String = "Кусь",
    override var health: Int = 150,
    override val idImageMonster: Int = R.drawable.nibbler,

    override val damageStrong: Int = 200,
    override val damageQuick: Int = 30,
    override val damageDeception: Int = 30,

    override val hitStrong: Int = 35,
    override val hitQuick: Int = 70,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 1.5,
    override val resistanceQuick: Double = 0.9,
    override val resistanceFeint: Double = 0.95,

    override val textHelp: Int = R.string.text_nibbler,
    override val ex: Int = 40
) : Monster
