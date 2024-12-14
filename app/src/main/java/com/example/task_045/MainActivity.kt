package com.example.task_045

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.example.task_045.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animationImage = AnimationUtils.loadAnimation(applicationContext, R.anim.visibility)
        binding.noteBookViewIV.startAnimation(animationImage)

        binding.captionTV

        binding.captionTV.animate().apply{
            translationX(0.0f)
        }.withEndAction{
            binding.captionTV.animate().apply {
                duration = 5000
                translationX(365.0f)
            }.start()
        }

        binding.openNoteBookBTN.animate().apply{
            translationX(0.0f)
        }.withEndAction{
            binding.openNoteBookBTN.animate().apply {
                duration = 5000
                translationX(365.0f)
            }.start()
        }

        binding.openNoteBookBTN.setOnClickListener{
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
        }
    }
}