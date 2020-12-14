package com.example.deinspection.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deinspection.CAR_NUMBER
import com.example.deinspection.R
import kotlinx.android.synthetic.main.activity_car.*

class CarActivity : AppCompatActivity() {
    var car : Int ? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        btnHelp1.setOnClickListener() {
            //check if list of current cars is equal max (3?)
            // if list of cars is max error message

            //else
            // list of cars append new empty one
            //go to page create new car

            /*
            if (size of list of car < max)
             */

            val intent = Intent(this, CarDetailsHelpActivity::class.java)
            //value = size of list of cars
            val value: Int = 3
            //TEMPORARIO
            intent.putExtra(CAR_NUMBER, value)
            //intent.putExtra(CAR_LIST, list)
            // send the list of all the cars, for us to add
            startActivity(intent)
        }
        btnInfo1.setOnClickListener() {
            //check if list of current cars is equal max (3?)
            // if list of cars is max error message

            //else
            // list of cars append new empty one
            //go to page create new car

            /*
            if (size of list of car < max)
             */

            val intent = Intent(this, CarDetailsInfoActivity::class.java)
            //value = size of list of cars
            val value: Int = 3
            //TEMPORARIO
            intent.putExtra(CAR_NUMBER, value)
            //intent.putExtra(CAR_LIST, list)
            // send the list of all the cars, for us to add
            startActivity(intent)
        }
        btnUpdate1.setOnClickListener() {
            //check if list of current cars is equal max (3?)
            // if list of cars is max error message

            //else
            // list of cars append new empty one
            //go to page create new car

            /*
            if (size of list of car < max)
             */

            val intent = Intent(this, EditDetailsActivity::class.java)
            //value = size of list of cars
            val value: Int = 3
            //TEMPORARIO
            intent.putExtra(CAR_NUMBER, value)
            //intent.putExtra(CAR_LIST, list)
            // send the list of all the cars, for us to add
            startActivity(intent)
        }

    }

    fun helpBtn(view: View){
        //val id : String = view.id.toString()
        //val i = id[-1].toInt() //this is the number of the attribute clicked


        // check the attribute related to the click

        //go to CarDetailsHelp
        val intent = Intent(this, CarDetailsHelpActivity::class.java)

        // save the car and the attribute
        // var car = 1, attr="oil"
        //something like that

        startActivity(intent)


    }

    fun infoBtn(view: View){
        val id : String = view.id.toString()
        val i = id[-1].toInt() //this is the number of the attribute clicked


        // check the attribute related to the click

        //go to CarDetailsInfo
        val intent = Intent(this, CarDetailsInfoActivity::class.java)

        // save the car and the attribute
        // var car = 1, attr="oil"
        //something like that

        startActivity(intent)


    }

    fun updateBtn(view: View){

        val id : String = view.id.toString()
        val i = id[-1].toInt() //this is the number of the attribute clicked



        // check the attribute related to the click
        // and update that
        //car.attribute.updateDate()


    }




}