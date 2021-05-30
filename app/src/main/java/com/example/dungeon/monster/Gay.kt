package com.example.dungeon.monster

import com.example.dungeon.R

data class Gay(
    override val name: String = "Гей",
    override var health: Int = 100,
    override val idImageMonster: Int = R.drawable.gay,

    override val damageStrong: Int = 70,
    override val damageQuick: Int = 80,
    override val damageDeception: Int = 90,

    override val hitStrong: Int = 50,
    override val hitQuick: Int = 80,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 0.95,
    override val resistanceQuick: Double = 1.0,
    override val resistanceFeint: Double = 1.1,

    override val textHelp: Int = R.string.text_gay,
    override val ex: Int = 40
) : Monster