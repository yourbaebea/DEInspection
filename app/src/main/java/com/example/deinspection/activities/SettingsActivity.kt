package com.example.deinspection.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deinspection.R
import kotlinx.android.synthetic.main.activity_settings.*

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import androidx.core.graphics.ColorUtils



import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar


class SettingsActivity : AppCompatActivity() {
    //this should be a global var, default time of hour or minute
    var hours : Int =9
    var minutes: Int =30


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        timeTv.text=" "+ hours.toString() + ":" + minutes.toString() //time for alarm

        //edit time for alarm
        pickTimeBtn.setOnClickListener() {
            val cal = Calendar.getInstance()
            val timeSet = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)
                timeTv.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(cal.time)
                hours = cal.get(Calendar.HOUR_OF_DAY)
                minutes= cal.get(Calendar.MINUTE)
            }
            TimePickerDialog(this, timeSet, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }







    }
}

