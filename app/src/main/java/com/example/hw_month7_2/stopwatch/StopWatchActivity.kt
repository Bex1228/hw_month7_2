package com.example.hw_month7_2.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_month7_2.databinding.ActivityStopWatchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class StopWatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStopWatchBinding
    private var job: Job? = null
    private var seconds: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startTimer()
        }

        binding.btnStop.setOnClickListener {
            stopTimer()
        }
    }

    private fun startTimer() {
        job?.cancel()

        job = CoroutineScope(Dispatchers.Main).launch {
            while (isActive && seconds <= 100000) {
                delay(1000)
                seconds++
                updateTimerText()
            }
        }
    }

    private fun stopTimer() {
        job?.cancel()
        seconds = 0
        updateTimerText()
    }

    private fun updateTimerText() {
        binding.tvTime.text = "$seconds"
    }
}