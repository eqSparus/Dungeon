package com.example.dungeon.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dungeon.R
import com.example.dungeon.config.EndGamePlayer
import com.example.dungeon.databinding.FragmentHallFameBinding


class HallFameFragment : Fragment() {

    /**Переменная для id с fragment_hall_fame*/
    private lateinit var binding: FragmentHallFameBinding

    companion object {
        @JvmStatic
        fun newInstance(): HallFameFragment = HallFameFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHallFameBinding.inflate(inflater, container, false)

        val firstPlayer = binding.firstPlace
        val secondPlayer = binding.secondPlace
        val thirdPlayer = binding.thirdPlace

        val listText = listOf(firstPlayer, secondPlayer, thirdPlayer)
        val endGame = EndGamePlayer(context!!.applicationContext)

        val betterPlayer = endGame.getBetterPlayer()
        betterPlayer.reverse()


        betterPlayer.forEachIndexed { index, pair ->
            val textPlayer = "Имя:${pair.first} счет:${pair.second}"
            listText[index].text = textPlayer
        }


        binding.buttonBack.setOnClickListener {
            MediaPlayer.create(context!!.applicationContext, R.raw.button_song).start()
            (activity as? BackHallFame)?.back()
        }
        return binding.root
    }

    interface BackHallFame {
        fun back()
    }


}