package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // 1. Declare variables
    private var mediaPlayer: MediaPlayer? = null
    private var totalTime: Int = 0
    private var isPrepared = false
    private val musicUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide the default top action bar
        supportActionBar?.hide()

        // 2. Find UI elements by ID
        val btnAutoplay = findViewById<ImageView>(R.id.autoplay)
        val btnPause = findViewById<ImageView>(R.id.pause)
        val btnStop = findViewById<ImageView>(R.id.stop)
        val seekbarMusic = findViewById<SeekBar>(R.id.seekBar)

        // 3. Initialize MediaPlayer for Streaming API
        setupMediaPlayer(seekbarMusic)

        // 5. Button Click Listeners
        btnAutoplay.setOnClickListener {
            if (mediaPlayer == null) {
                setupMediaPlayer(seekbarMusic)
            }
            if (isPrepared) {
                mediaPlayer?.start()
                Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Loading music...", Toast.LENGTH_SHORT).show()
            }
        }

        btnPause.setOnClickListener {
            mediaPlayer?.pause()
        }

        btnStop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            isPrepared = false
            seekbarMusic.progress = 0
            Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show()
        }

        // 6. SeekBar Change Listener (When User drags the SeekBar)
        seekbarMusic.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // 7. Handler to update SeekBar automatically as music plays
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    mediaPlayer?.let {
                        if (it.isPlaying) {
                            seekbarMusic.progress = it.currentPosition
                        }
                    }
                    handler.postDelayed(this, 1000)
                } catch (exception: Exception) {
                    seekbarMusic.progress = 0
                }
            }
        }, 0)
    }

    private fun setupMediaPlayer(seekbarMusic: SeekBar) {
        try {
            mediaPlayer = MediaPlayer().apply {
                setDataSource(musicUrl)
                prepareAsync()
                setOnPreparedListener {
                    isPrepared = true
                    totalTime = it.duration
                    seekbarMusic.max = totalTime
                    // Enable Autoplay on load
                    it.start()
                    Toast.makeText(this@MainActivity, "Music Loaded & Playing", Toast.LENGTH_SHORT).show()
                }
                setOnErrorListener { _, what, extra ->
                    Log.e("MediaPlayer", "Error occurred: $what, $extra")
                    false
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to load music from API", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
