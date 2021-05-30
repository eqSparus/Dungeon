package com.example.dungeon.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.dungeon.R
import com.example.dungeon.config.NameFile
import com.example.dungeon.config.Requests
import com.example.dungeon.databinding.ActivityGameBinding
import com.example.dungeon.fragment.PumpingFragment
import com.example.dungeon.model.Player
import com.google.gson.Gson
import java.util.*
import kotlin.concurrent.schedule

class GameActivity : AppCompatActivity() {

    /**Перемменая для id с activity_game*/
    private lateinit var binding: ActivityGameBinding

    /**Кнопки дверей*/
    private lateinit var doorOne: ImageButton
    private lateinit var doorTwo: ImageButton

    /**Данные об игроке*/
    private lateinit var player: Player

    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences("savePlayer", Context.MODE_PRIVATE)

        doorOne = binding.doorOne
        doorTwo = binding.doorTwo
        val status = intent.getStringExtra("statusGame")
        if (status == Requests.NEW_GAME) {
            val namePlayer = intent.getStringExtra("namePlayer").toString()
            player = Player(namePlayer)
        } else if (status == Requests.LOAD_GAME) {
            player = getSavaPlayer()
        }
        binding.namePlayer.text = player.name
        playerStatusUpdates()
    }

    /**Открытия арены*/
    fun doorClick(view: View) {
        MediaPlayer.create(applicationContext, R.raw.door_open).start()
        val intent = Intent(this, ArenaActivity::class.java).apply {
            putExtra(Requests.GAME_STATUS_PLAYER, player)
        }
        when (view.id) {
            R.id.doorOne -> doorOne.setImageResource(R.drawable.open_door)
            R.id.doorTwo -> doorTwo.setImageResource(R.drawable.open_door)
        }
        Timer().schedule(300) {
            startActivityForResult(intent, Requests.GAME_ARENA_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Requests.GAME_ARENA_CODE && resultCode == RESULT_OK && data != null) {
            doorOne.setImageResource(R.drawable.door_one)
            doorTwo.setImageResource(R.drawable.door_two)
            player = data.getSerializableExtra(Requests.GAME_STATUS_PLAYER) as Player
            playerStatusUpdates()
        }
    }

    /**Обновления данных об игроке*/
    private fun playerStatusUpdates() {
        binding.healthPlayer.progress = player.health
        binding.potionsTv.text = player.potions.toString()
        binding.goldTv.text = player.gold.toString()
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

    private fun getSavaPlayer(): Player {
        val stl = pref.getString(NameFile.KEY_SAVE_PLAYER, null)
        stl?.let {
            return Gson().fromJson(it, Player::class.java)
        }
        return Player("Player")
    }

    fun clickSaveButton(view: View) {
        MediaPlayer.create(applicationContext, R.raw.button_song).start()
        pref.edit {
            putString(NameFile.KEY_SAVE_PLAYER, Gson().toJson(player))
        }
    }

    fun pumpingClick(view: View) {
        supportFragmentManager.beginTransaction()
            .replace(binding.pumpingFragment.id, PumpingFragment.newInstance(player))
            .addToBackStack(null)
            .commit()
    }
}