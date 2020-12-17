package com.example.deinspection

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi

import com.example.deinspection.activities.MainMenuActivity


import kotlinx.android.synthetic.main.activity_init_app.*
import com.example.deinspection.classes.*

const val ATTRIBUTE = "attribute"

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_app)


        // read from bin file
        // ver vars needed to global var
        //something like that

        //when reading is finished : or set an animation for the loading bar
        //activity main is the first page on the app, just the logo + loading bar(?)
        //

        // animation of the logo
        // rn its the wrong pic
        // waiting for the png without background
        //val firstanim = AnimationUtils.loadAnimation(this, R.anim.firstpage)


        //Toast.makeText(this, "new animation", Toast.LENGTH_LONG).show()
        //logocar.startAnimation(firstanim)

        loaded()
    }

    private fun loaded(){
        //go to MainMenu
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        
    }


    class Receiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("MainActivity", " Recebido")

        }
    }






}
