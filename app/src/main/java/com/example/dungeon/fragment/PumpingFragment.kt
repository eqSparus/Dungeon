package com.example.dungeon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dungeon.control.Pumping
import com.example.dungeon.databinding.FragmentPumpingBinding
import com.example.dungeon.model.Player


class PumpingFragment : Fragment() {

    private lateinit var binding: FragmentPumpingBinding
    private lateinit var player: Player

    private lateinit var pointPlayer: TextView

    private lateinit var damageText: TextView
    private lateinit var evasionText: TextView
    private lateinit var probabilityText: TextView

    private lateinit var pumping :Pumping

    companion object {
        @JvmStatic
        fun newInstance(): PumpingFragment = PumpingFragment()

        @JvmStatic
        fun newInstance(player: Player): PumpingFragment {
            val pumpingFragment = PumpingFragment()
            pumpingFragment.player = player
            pumpingFragment.pumping = Pumping(player)
            return pumpingFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPumpingBinding.inflate(inflater, container, false)
        pointPlayer = binding.pointPlayer
        damageText = binding.damage
        evasionText = binding.evasion
        probabilityText = binding.probability

        binding.damageButtonPlus.setOnClickListener {
            pumping.setDamage()
            updateStat()
        }

        binding.evasionButtonPlus.setOnClickListener {
            pumping.setEvasion()
            updateStat()
        }

        binding.probabilityButtonPlus.setOnClickListener{
            pumping.setHitProbability()
            updateStat()
        }

        binding.backButton.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.lvlPlayer.text = "lvl:${player.level}"
        binding.experiencePlayer.progress = player.experience
        binding.lvlArena.text = "Арена:${player.lvlArena}"
        updateStat()
    }


    private fun updateStat() {
        pointPlayer.text = "Очки:${player.point}"
        damageText.text = "Урон: ${player.damage}"
        evasionText.text = "Уклонение: ${player.evasion}"
        probabilityText.text = "Шанс попадания: ${player.hitProbability}"
    }

}