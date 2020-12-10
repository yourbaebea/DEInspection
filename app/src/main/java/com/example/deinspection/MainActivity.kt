package com.example.deinspection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deinspection.activities.CarActivity
import com.example.deinspection.activities.NewCarActivity

const val CAR_NUMBER = "com.example.deinspection.CAR"

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)


        //activity main is the first page on the app, just the logo + loading bar(?)
        //wait(10)
        loaded()
    }

    fun loaded(){
        // go to the next page, main menu
        setContentView(R.layout.main_menu)
        
    }


    fun seeCar(view: View) {

        // if any of the btnCar is clicked

        var value: Int = 0
        if(view.id.toString() == "btnCar1") value = 1
        if(view.id.toString() == "btnCar2") value = 2
        if(view.id.toString() == "btnCar3") value = 3

        // check if value == 1, thats an error
        val intent = Intent(this, CarActivity::class.java)
        intent.putExtra(CAR_NUMBER, value)
        //intent.putExtra(CAR_LIST, list)
        // send the list of all the cars
        startActivity(intent)


    }



    fun addNewCar(view: View) {


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
        var value: Int= 3
        //TEMPORARIO
        intent.putExtra(CAR_NUMBER, value)
        //intent.putExtra(CAR_LIST, list)
        // send the list of all the cars, for us to add
        startActivity(intent)


    }

}