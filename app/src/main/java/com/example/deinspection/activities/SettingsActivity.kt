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
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.Toast
import com.example.deinspection.MainActivity

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
                //
                // save the time the alarms go off on a global var
                //
            }
            TimePickerDialog(this, timeSet, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        //no pop up, the only info changed in this page is the button to change the time the alarms go off
        btnBack.setOnClickListener() {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        btnInstagram.setOnClickListener(){
            //open instagram or something
        }

        btnFacebook.setOnClickListener(){
            //open facebook or something
        }

        btnFeedback.setOnClickListener(){
            //open google play rating
            //or form?
            // i would ratter go to google play!!
        }

        btnMoreAbout.setOnClickListener(){
            val intent = Intent(this, MoreAboutActivity::class.java)

            // save the car and the attribute
            // var car = 1, attr="oil"
            //something like that

            startActivity(intent)

        }

        btnBack.setOnClickListener(){
            val intent = Intent(this, MainMenuActivity::class.java)


            startActivity(intent)
        }

        btnError.setOnClickListener(){
            //open our email or report error in google play idk something or delete this button
        }

        btnReset.setOnClickListener() {
            //pop up
            //do you want to leave without saving?
            val dialog: AlertDialog = AlertDialog.Builder(this)
                    .setTitle("DEInspection")
                    .setMessage("Pretende eliminar tudo e reininciar a app?")
                    .setPositiveButton("Sim", null)
                    .setNegativeButton("Cancelar", null)
                    .show()
            val positiveButton: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val negativeButton: Button = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

            //reset
            positiveButton.setOnClickListener() {
                dialog.dismiss()
                //dele all cars,and reset application
                //goes to the initial activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            // cancel
            negativeButton.setOnClickListener() {
                dialog.dismiss()
            }
        }






    }
}

