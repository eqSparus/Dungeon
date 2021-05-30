package com.example.dungeon.monster

import com.example.dungeon.R

data class Skeleton(
    override val name: String = "Дрищ",
    override var health: Int = 30,
    override val idImageMonster: Int = R.drawable.skeleton,

    override val damageStrong: Int = 80,
    override val damageQuick: Int = 120,
    override val damageDeception: Int = 20,

    override val hitStrong: Int = 30,
    override val hitQuick: Int = 75,
    override val hitFeint: Int = 100,

    override val resistanceStrong: Double = 0.95,
    override val resistanceQuick: Double = 1.2,
    override val resistanceFeint: Double = 10000000.0,

    override val textHelp: Int = R.string.text_skeleton,
    override val ex: Int = 20
) : Monster
