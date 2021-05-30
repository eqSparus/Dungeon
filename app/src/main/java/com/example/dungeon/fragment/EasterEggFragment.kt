package com.example.dungeon.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dungeon.R
import com.example.dungeon.databinding.FragmentEasterEggBinding

class EasterEggFragment : Fragment() {

    private lateinit var binding: FragmentEasterEggBinding

    companion object {
        @JvmStatic
        fun newInstance(): EasterEggFragment = EasterEggFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEasterEggBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            MediaPlayer.create(context!!.applicationContext, R.raw.button_song).start()
            (activity as? Back)?.backFragment()
        }
        return binding.root
    }

    interface Back {
        fun backFragment()
    }

}