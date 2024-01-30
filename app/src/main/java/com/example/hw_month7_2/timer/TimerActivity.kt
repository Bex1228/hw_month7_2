package com.example.hw_month7_2.timer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_month7_2.databinding.ActivityTimerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartTimer.setOnClickListener {
            val timeInSeconds = binding.etTimer.text.toString().toLong()
            startTimer(timeInSeconds)
        }
    }

    private fun startTimer(timeInSeconds: Long) {
        job?.cancel()

        job = CoroutineScope(Dispatchers.Main).launch {
            for (secondsRemaining in timeInSeconds downTo 0) {
                delay(1000)
                updateCountdownText(secondsRemaining)
            }
            showToast("Timer completed!")
        }
    }

    private fun updateCountdownText(secondsRemaining: Long) {
        runOnUiThread {
            binding.tvTimer.text = "Time left: $secondsRemaining seconds"
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
