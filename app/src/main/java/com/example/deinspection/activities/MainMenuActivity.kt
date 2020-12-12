package com.example.deinspection.activities

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deinspection.CAR_NUMBER
import com.example.deinspection.R
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.activity_settings.*
import java.text.SimpleDateFormat
import java.util.*

class MainMenuActivity : AppCompatActivity() {
    var value: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        btnCar1.setOnClickListener() {
            seeCar(1)
        }
        btnCar2.setOnClickListener() {
            seeCar(2)
        }
        btnCar3.setOnClickListener() {
            seeCar(3)
        }
        btnCar4.setOnClickListener() {
            seeCar(4)
        }
        btnCar5.setOnClickListener() {
            seeCar(5)
        }

        btnSettings.setOnClickListener() {
            //check if list of current cars is equal max (3?)
            // if list of cars is max error message

            //else
            // list of cars append new empty one
            //go to page create new car

            /*
            if (size of list of car < max)
             */

            val intent = Intent(this, SettingsActivity::class.java)
            //value = size of list of cars
            var value: Int = 3
            //TEMPORARIO
            intent.putExtra(CAR_NUMBER, value)
            //intent.putExtra(CAR_LIST, list)
            // send the list of all the cars, for us to add
            startActivity(intent)
        }

        btnAddCar.setOnClickListener() {
            //check if list of current cars is equal max (3?)
            // if list of cars is max error message

            //else
            // list of cars append new empty one
            //go to page create new car

            /*
            if (size of list of car < max)
             */

            val intent = Intent(this, NewCarActivity::class.java)
            //value = size of list of cars
            val value: Int = 3
            //TEMPORARIO
            intent.putExtra(CAR_NUMBER, value)
            //intent.putExtra(CAR_LIST, list)
            // send the list of all the cars, for us to add
            startActivity(intent)
        }

    }




    //aux to the listeners to see the car number n
    fun seeCar(n: Int) {

        // check if value == 1, thats an error
        val intent = Intent(this, CarActivity::class.java)
        intent.putExtra(CAR_NUMBER, value)
        //intent.putExtra(CAR_LIST, list)
        // send the list of all the cars
        startActivity(intent)
    }


}

