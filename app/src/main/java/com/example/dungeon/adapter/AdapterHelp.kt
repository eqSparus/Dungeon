package com.example.dungeon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import com.example.dungeon.databinding.ItemListAdviceBinding
import com.example.dungeon.monster.Monster

/**
 * Адаптер для вывода информации о монстрах
 */
class AdapterHelp(
    @NonNull private val applicationContext: Context,
    @LayoutRes private val listView: Int,
    @NonNull private val arr: List<Monster>
) :
    ArrayAdapter<Monster>(applicationContext, listView, arr) {

    private lateinit var binding: ItemListAdviceBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        binding = ItemListAdviceBinding.inflate(LayoutInflater.from(applicationContext))


        binding.monsterImage.setImageResource(arr[position].idImageMonster)
        binding.nameMonster.text = "Имя: ${arr[position].name}"
        binding.swipeAttack.text = "Сильная атака: ${arr[position].resistanceStrong}"
        binding.quickAttack.text = "Быстрая атака: ${arr[position].resistanceQuick}"
        binding.counterstrikeAttack.text = "Финт: ${arr[position].resistanceFeint}"
        binding.textHelp.text = applicationContext.getString(arr[position].textHelp)
        return binding.root
    }
}