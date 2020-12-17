package com.example.deinspection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast

import com.example.deinspection.activities.MainMenuActivity


import kotlinx.android.synthetic.main.activity_init_app.*
import com.example.deinspection.classes.*

const val ATTRIBUTE = "attribute"

class MainActivity : AppCompatActivity() {
    companion object {
        val carList: MutableList<Car> = ArrayList()
    }


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
        val firstanim = AnimationUtils.loadAnimation(this, R.anim.firstpage)


        Toast.makeText(this, "new animation", Toast.LENGTH_LONG).show()
        logocar.startAnimation(firstanim)

        loaded()
    }

    private fun loaded(){
        //go to MainMenu
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        
    }





}