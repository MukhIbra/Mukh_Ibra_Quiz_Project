package com.example.mukh_ibra_quiz_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btneasy.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
            btneasy.startAnimation(scaleAnimation)
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("level", "Easy")
            startActivity(intent)
        }
        btnmedium.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
            btnmedium.startAnimation(scaleAnimation)
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("level", "Medium")
            startActivity(intent)
        }
        btnhard.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
            btnhard.startAnimation(scaleAnimation)
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("level", "Hard")
            startActivity(intent)
        }
    }
}