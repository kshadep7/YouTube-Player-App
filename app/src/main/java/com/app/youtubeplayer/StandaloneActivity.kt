package com.app.youtubeplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.standalone_activity.*

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.standalone_activity)

        btnPlayVideo.setOnClickListener(this)
        btnPlayPlaylist.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        val intent = when (v.id) {
            R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                this,
                getString(R.string.GOOGLE_API_KEY),
                YOUTUBE_ID,
                0, true, true
            )
            R.id.btnPlayPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                this,
                getString(R.string.GOOGLE_API_KEY),
                YOUTUBE_PLAYLIST_ID,
                0, 0, true, false
            )
            else -> throw IllegalArgumentException("Undefined button reference")
        }
        startActivity(intent)
    }
}
