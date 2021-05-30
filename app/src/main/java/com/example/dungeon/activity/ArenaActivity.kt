package com.example.dungeon.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.example.dungeon.R
import com.example.dungeon.config.EndGamePlayer
import com.example.dungeon.config.Requests
import com.example.dungeon.control.Battle
import com.example.dungeon.control.PlayerAwards
import com.example.dungeon.control.Pumping
import com.example.dungeon.databinding.ActivityArenaBinding
import com.example.dungeon.model.Player
import com.example.dungeon.monster.*
import java.util.*
import kotlin.concurrent.schedule

class ArenaActivity : AppCompatActivity() {

    /**Перемменая для id с activity_arena*/
    private lateinit var binding: ActivityArenaBinding

    /**Переменные статусов игры*/
    private lateinit var statusGame: TextView

    /**Переменные для игрового процесса */
    private lateinit var player: Player
    private lateinit var battle: Battle
    private lateinit var playerAwards: PlayerAwards

    /**Часть интерфейса отвечающая за сражения*/
    private lateinit var groupBattle: Group

    private lateinit var pumping: Pumping

    /**переменная для выбора монстра уровня  */
    private lateinit var monster: Monster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArenaBinding.inflate(layoutInflater)
        statusGame = binding.statusGame
        groupBattle = binding.groupBattle
        setContentView(binding.root)
        player = intent.getSerializableExtra(Requests.GAME_STATUS_PLAYER) as Player
        monster = if (player.lvlArena < 21) {
            val arrMonsterOne = listOf(Jew(), Nibbler(), Ogre(), OneEye(), Skeleton())
            binding.arena.setBackgroundResource(R.drawable.battle_arena)
            arrMonsterOne[(arrMonsterOne.indices).random()]
        } else {
            val arrMonsterTwo = listOf(Mushroom(), Death(), Transgender(), Nigger(), Gay())
            binding.arena.setBackgroundResource(R.drawable.arena_two)
            arrMonsterTwo[(arrMonsterTwo.indices).random()]
        }
        binding.namePlayer.text = player.name
        playerAwards = PlayerAwards(player)
        battle = Battle(player, monster)
        binding.doorArena.isClickable = false
        statusUpdates(true)
        pumping = Pumping(player)
        when ((0..1).random()) {
            0 -> setTreasure()

            1 -> {
                binding.healthMonster.max = monster.health
                binding.healthMonster.progress = monster.health
                binding.monsterName.text = monster.name
                binding.imageMonster.setImageResource(monster.idImageMonster)
                groupBattle.visibility = View.VISIBLE
            }
        }
    }

    /**Нанесение ударов*/
    fun hitClick(view: View) {
        binding.swipe.isClickable = false
        binding.quick.isClickable = false
        binding.counterstrike.isClickable = false
        var stGame = ""
        when (view.id) {
            binding.swipe.id -> {
                MediaPlayer.create(applicationContext, R.raw.swipe).start()
                stGame = battle.getDamageReception(1)
            }
            binding.quick.id -> {
                MediaPlayer.create(applicationContext, R.raw.quick).start()
                stGame = battle.getDamageReception(2)
            }
            binding.counterstrike.id -> {
                MediaPlayer.create(applicationContext, R.raw.counterstrike).start()
                stGame = battle.getDamageReception(3)
            }
        }
        statusGame.text = stGame
        statusGame.visibility = View.VISIBLE
        statusUpdates(false)

        Timer().schedule(1000) {
            runOnUiThread {
                if (battle.isVictoryPlayer()) {
                    MediaPlayer.create(applicationContext, R.raw.dead_monster).start()
                    val st = "${player.name} победил"
                    statusGame.text = st
                    groupBattle.visibility = View.GONE
                    pumping.setExperience(monster.ex)
                    Timer().schedule(1000) {
                        runOnUiThread {
                            statusGame.visibility = View.GONE
                            setTreasure()
                        }
                    }
                } else {
                    statusGame.visibility = View.GONE
                    attackMonster()
                    statusUpdates(true)
                }
            }
        }

    }

    /**Использование зелей*/
    fun drinkPotions(view: View) {
        MediaPlayer.create(applicationContext, R.raw.pottion_button).start()
        statusGame.text = battle.drinkPotions()
        statusGame.visibility = View.VISIBLE
        statusUpdates(true)
        Timer().schedule(1000) {
            runOnUiThread {
                statusGame.visibility = View.GONE
                if (groupBattle.visibility == View.VISIBLE) attackMonster()
            }
        }
    }

    /**Метод отвечает за вывод атаки монстра на экран*/
    private fun attackMonster() {
        MediaPlayer.create(applicationContext, R.raw.monster_attack).start()
        statusGame.text = battle.monsterReception()
        statusGame.visibility = View.VISIBLE
        Timer().schedule(1000) {
            runOnUiThread {
                if (battle.isVictoryMonster()) {
                    MediaPlayer.create(applicationContext, R.raw.dead).start()
                    groupBattle.visibility = View.GONE
                    val st = "${monster.name} победил"
                    statusGame.text = st
                    Timer().schedule(2000) {
                        runOnUiThread {
                            statusGame.visibility = View.GONE
                            endGame()
                        }
                    }
                } else {
                    statusGame.visibility = View.GONE
                    binding.swipe.isClickable = true
                    binding.quick.isClickable = true
                    binding.counterstrike.isClickable = true
                }
            }
        }
    }

    /**Добавление награды игроку*/
    private fun setTreasure() {
        val chestButton = binding.buttonChest
        chestButton.visibility = View.VISIBLE
        chestButton.setOnClickListener {
            MediaPlayer.create(applicationContext, R.raw.awords).start()
            chestButton.setImageResource(R.drawable.chest_open)
            statusGame.text = playerAwards.getTreasure()
            statusGame.visibility = View.VISIBLE
            chestButton.isClickable = false
            statusUpdates(true)
            Timer().schedule(1000) {
                runOnUiThread {
                    chestButton.visibility = View.GONE
                    statusGame.visibility = View.GONE
                    binding.doorArena.isClickable = true
                    player.lvlArena++
                }
            }
        }
    }

    /**Выход с арены*/
    fun arenaDoorClick(view: View) {
        MediaPlayer.create(applicationContext, R.raw.door_open).start()
        view as ImageButton
        view.setImageResource(R.drawable.open_door_battle)
        intent.putExtra(Requests.GAME_STATUS_PLAYER, player)
        setResult(RESULT_OK, intent)
        Timer().schedule(300) {
            finish()
        }
    }

    /**Обновления статусов*/
    private fun statusUpdates(person: Boolean) {
        if (person) {
            binding.healthPlayer.progress = player.health
            binding.potionsTv.text = player.potions.toString()
            binding.goldTv.text = player.gold.toString()
        } else {
            binding.healthMonster.progress = monster.health
        }
    }

    /**Метод конца игры*/
    private fun endGame() {
        val endGame = EndGamePlayer(applicationContext)
        endGame.checkBetterPlayer(player)
        Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
        }
    }

    /*** Методы для полноэкранного режима*/
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    @Deprecated("Метод устрел найти замену", ReplaceWith("hideSystemUI()"))
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}