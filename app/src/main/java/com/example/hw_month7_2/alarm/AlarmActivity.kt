package com.example.hw_month7_2.alarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_month7_2.databinding.ActivityAlarmBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class AlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmBinding
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        binding.btnSetAlarm.setOnClickListener {
            val hour: Int
            val minute: Int
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                hour = binding.timePicker.hour
                minute = binding.timePicker.minute
            } else {
                hour = binding.timePicker.currentHour
                minute = binding.timePicker.currentMinute
            }
            val alarmTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                if (timeInMillis <= System.currentTimeMillis()) {
                    add(Calendar.DAY_OF_YEAR, 1)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                setAlarm(alarmTime.timeInMillis)
            }
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private suspend fun setAlarm(timeInMillis: Long) {
        withContext(Dispatchers.Default) {
            val intent = Intent(this@AlarmActivity, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                this@AlarmActivity, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
        }
    }
}
