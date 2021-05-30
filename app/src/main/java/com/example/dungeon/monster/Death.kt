package com.example.dungeon.monster

import com.example.dungeon.R

data class Death(
    override val name: String = "Некрофил",
    override var health: Int = 100,
    override val idImageMonster: Int = R.drawable.death,

    override val damageStrong: Int = 80,
    override val damageQuick: Int = 110,
    override val damageDeception: Int = 130,

    override val hitStrong: Int = 50,
    override val hitQuick: Int = 80,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 0.95,
    override val resistanceQuick: Double = 1.1,
    override val resistanceFeint: Double = 1.05,

    override val textHelp: Int = R.string.text_death,
    override val ex: Int = 45
) : Monster