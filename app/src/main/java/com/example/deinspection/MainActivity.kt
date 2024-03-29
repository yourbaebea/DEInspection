package com.example.deinspection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.BroadcastReceiver
import android.content.Context

import com.example.deinspection.activities.MainMenuActivity


import kotlinx.android.synthetic.main.activity_init_app.*
import com.example.deinspection.classes.*

const val ATTRIBUTE = "attribute"

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_app)

        // read from bin file
        // ver vars needed to global var
        //something like that

        //when reading is finished : or set an animation for the loading bar
        //activity main is the first page on the app, just the logo + loading bar(?)
        //




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