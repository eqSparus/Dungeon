package com.example.dungeon.monster

import com.example.dungeon.R


data class Ogre(
    override val name: String = "Синтольщик",
    override var health: Int = 90,
    override val idImageMonster: Int = R.drawable.monster_ogre,

    override val damageStrong: Int = 150,
    override val damageQuick: Int = 40,
    override val damageDeception: Int = 10,

    override val hitStrong: Int = 40,
    override val hitQuick: Int = 30,
    override val hitFeint: Int = 30,

    override val resistanceStrong: Double = 1.5,
    override val resistanceQuick: Double = 0.8,
    override val resistanceFeint: Double = 0.85,

    override val textHelp: Int = R.string.text_ogre,
    override val ex: Int = 30
) : Monster