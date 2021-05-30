package com.example.dungeon.activity

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dungeon.R
import com.example.dungeon.config.Requests
import com.example.dungeon.databinding.ActivityMenuBinding
import com.example.dungeon.fragment.AdviceHelpFragment
import com.example.dungeon.fragment.EasterEggFragment
import com.example.dungeon.fragment.HallFameFragment

class MainActivity : AppCompatActivity(), AdviceHelpFragment.OnClickBack,HallFameFragment.BackHallFame,
EasterEggFragment.Back{

    /**Переменная с id activity_main_menu*/
    private lateinit var binding: ActivityMenuBinding

    /**Ввод имени игрока*/
    private lateinit var namePlayerText: EditText

    /**Фрагменты "помощи и советы" и "Доски почета"*/
    private val adviceHelp = AdviceHelpFragment.newInstance()
    private val hallFame = HallFameFragment.newInstance()
    private val easterEgg = EasterEggFragment.newInstance()

    private lateinit var mediaSong:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaSong = MediaPlayer.create(applicationContext, R.raw.song_main)
        mediaSong.isLooping = true
        volumeControlStream = AudioManager.STREAM_MUSIC
        namePlayerText = binding.editNamePlayer
    }

    fun startGame(view: View) {
        MediaPlayer.create(applicationContext,R.raw.button_song).start()
        val text = namePlayerText.text.toString().trim(' ')
        if (text.isNotBlank()) {
            namePlayerText.text.clear()
            Intent(this, GameActivity::class.java).apply {
                putExtra("statusGame", Requests.NEW_GAME)
                putExtra("namePlayer", text)
                startActivity(this)
            }
        } else {
            Toast.makeText(applicationContext, "Введите имя персонажа", Toast.LENGTH_SHORT).show()
        }

        applicationContext.filesDir.absolutePath
    }

    fun loadGams(view: View) {
        MediaPlayer.create(applicationContext,R.raw.button_song).start()
        Intent(this, GameActivity::class.java).apply {
            putExtra("statusGame", Requests.LOAD_GAME)
            startActivity(this)
        }
    }

    fun clickHallFame(view: View) {
        sk()
        supportFragmentManager.beginTransaction()
            .replace(binding.frameHelp.id, hallFame)
            .commit()
    }

    override fun back() {
        binding.frameHelp.visibility = View.GONE
        binding.groupMenu.visibility = View.VISIBLE
        binding.egg.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .remove(hallFame)
            .commit()
    }

    fun clickHelp(view: View) {
        sk()
        supportFragmentManager.beginTransaction()
            .replace(binding.frameHelp.id, adviceHelp)
            .commit()
    }

    override fun backClick() {
        binding.frameHelp.visibility = View.GONE
        binding.groupMenu.visibility = View.VISIBLE
        binding.egg.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .remove(adviceHelp)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        mediaSong.start()
    }

    override fun onPause() {
        super.onPause()
        mediaSong.pause()
    }


    /*** Методы для полноэкранного режима*/
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    @Deprecated("Метод устрел найти замену", ReplaceWith("hideSystemUI()"))
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    override fun backFragment() {
        binding.frameHelp.visibility = View.GONE
        binding.groupMenu.visibility = View.VISIBLE
        binding.egg.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .remove(easterEgg)
            .commit()
    }

    fun eggOpen(view: View) {
        sk()
        supportFragmentManager.beginTransaction()
            .replace(binding.frameHelp.id, easterEgg)
            .commit()
    }

    private fun sk(){
        MediaPlayer.create(applicationContext,R.raw.button_song).start()
        binding.frameHelp.visibility = View.VISIBLE
        binding.groupMenu.visibility = View.GONE
        binding.egg.visibility = View.GONE
    }

}