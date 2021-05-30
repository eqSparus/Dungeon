package com.example.dungeon.monster

import com.example.dungeon.R

data class Transgender(
    override val name: String = "Трансгендер",
    override var health: Int = 80,
    override val idImageMonster: Int = R.drawable.trans,

    override val damageStrong: Int = 70,
    override val damageQuick: Int = 70,
    override val damageDeception: Int = 100,

    override val hitStrong: Int = 40,
    override val hitQuick: Int = 80,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 0.9,
    override val resistanceQuick: Double = 0.95,
    override val resistanceFeint: Double = 1.1,

    override val textHelp: Int = R.string.text_trans,
    override val ex: Int = 35
) : Monster