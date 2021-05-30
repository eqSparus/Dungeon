package com.example.dungeon.monster

import com.example.dungeon.R


data class Jew(
    override val name: String = "Бедный Еврей",
    override var health: Int = 50,
    override val idImageMonster: Int = R.drawable.jew,

    override val damageStrong: Int = 50,
    override val damageQuick: Int = 80,
    override val damageDeception: Int = 120,

    override val hitStrong: Int = 20,
    override val hitQuick: Int = 50,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 1.0,
    override val resistanceQuick: Double = 2.0,
    override val resistanceFeint: Double = 10000000.0,

    override val textHelp: Int = R.string.text_jew,

    override val ex: Int = 20
) : Monster




