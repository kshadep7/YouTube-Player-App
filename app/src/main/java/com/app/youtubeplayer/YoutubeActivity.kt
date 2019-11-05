package com.app.youtubeplayer

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_ID = "zuADwUGI4RU"
const val YOUTUBE_PLAYLIST_ID = ""

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val tag = "YoutubeActivity"

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        wasrestored: Boolean
    ) {
        Log.d(tag, "onInitializationSuccess: provider is $provider")
        Log.d(tag, "onInitializationSuccess: youTubePlayer is $youTubePlayer")
        Toast.makeText(this, "YouTube Player initialization successful", Toast.LENGTH_SHORT).show()

        if (!wasrestored)
            youTubePlayer?.cueVideo(YOUTUBE_ID)

    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val requestCode = 0

        if (youTubeInitializationResult?.isUserRecoverableError == true) {
            youTubeInitializationResult.getErrorDialog(this, requestCode)?.show()
        } else {
            Toast.makeText(this, youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 400)
//        button1.text = "Button Added"
//        layout.addView(button1)

        val youtubePlayerView = YouTubePlayerView(this)
        youtubePlayerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(youtubePlayerView)

        youtubePlayerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }
}
