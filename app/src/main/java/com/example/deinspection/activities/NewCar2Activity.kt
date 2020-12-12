package com.example.deinspection.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deinspection.R
import kotlinx.android.synthetic.main.activity_new_car_2.*

class NewCar2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car_2)
        //seekBar()

        //var car = Car()
        // idk how to do this uwu

        btnBackNC2.setOnClickListener() {

            //pop up
            //do you want to leave without saving?



            //save definitions
            val intent = Intent(this, NewCarActivity::class.java)
            //send only check options to set definitions
            startActivity(intent)

        }


        btnNext2.setOnClickListener(){
            //save definitions
            val intent = Intent(this, NewCar3Activity::class.java)
            //send only check options to set definitions
            startActivity(intent)
        }

    }



}


