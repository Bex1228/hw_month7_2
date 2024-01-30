package com.example.hw_month7_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_month7_2.alarm.AlarmActivity
import com.example.hw_month7_2.databinding.ActivityMainBinding
import com.example.hw_month7_2.stopwatch.StopWatchActivity
import com.example.hw_month7_2.timer.TimerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        binding.btnAlarm.setOnClickListener {
            val intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        }

        binding.btnStopwatch.setOnClickListener {
            val intent = Intent(this, StopWatchActivity::class.java)
            startActivity(intent)
        }

        binding.btnTimer.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
    }
}