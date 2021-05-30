package com.example.dungeon.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dungeon.R
import com.example.dungeon.adapter.AdapterHelp
import com.example.dungeon.databinding.FragmentAdviceHelpBinding
import com.example.dungeon.monster.*


class AdviceHelpFragment : Fragment() {

    /**Переменная для id с fragment_advice_help*/
    private lateinit var binding: FragmentAdviceHelpBinding

    /**Массив с монстрами*/
    private val listMonster =
        listOf(Jew(), Nibbler(), Ogre(), OneEye(), Skeleton(), Mushroom(), Death(), Transgender(), Nigger(), Gay())

    companion object {
        @JvmStatic
        fun newInstance(): AdviceHelpFragment = AdviceHelpFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAdviceHelpBinding.inflate(inflater, container, false)

        binding.listHelp.adapter = AdapterHelp(context!!.applicationContext, R.layout.item_list_advice, listMonster)

        binding.backButton.setOnClickListener {
            MediaPlayer.create(context!!.applicationContext, R.raw.button_song).start()
            (activity as? OnClickBack)?.backClick()
        }
        return binding.root
    }


    interface OnClickBack {
        fun backClick()
    }

}