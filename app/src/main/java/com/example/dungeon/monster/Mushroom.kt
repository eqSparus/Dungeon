package com.example.dungeon.monster

import com.example.dungeon.R

data class Mushroom(
    override val name: String = "Гриб нацист",
    override var health: Int = 120,
    override val idImageMonster: Int = R.drawable.mushroom,

    override val damageStrong: Int = 130,
    override val damageQuick: Int = 100,
    override val damageDeception: Int = 80,

    override val hitStrong: Int = 30,
    override val hitQuick: Int = 70,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 1.0,
    override val resistanceQuick: Double = 2.5,
    override val resistanceFeint: Double = 0.8,

    override val textHelp: Int = R.string.text_mushroom,
    override val ex: Int = 60
) : Monster
