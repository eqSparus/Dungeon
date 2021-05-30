package com.example.dungeon.monster

import com.example.dungeon.R

data class Nigger(
    override val name: String = "Нигер",
    override var health: Int = 120,
    override val idImageMonster: Int = R.drawable.nigger,

    override val damageStrong: Int = 80,
    override val damageQuick: Int = 120,
    override val damageDeception: Int = 50,

    override val hitStrong: Int = 40,
    override val hitQuick: Int = 70,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 1.0,
    override val resistanceQuick: Double = 1.1,
    override val resistanceFeint: Double = 0.9,

    override val textHelp: Int = R.string.text_nigger,
    override val ex: Int = 30
) : Monster