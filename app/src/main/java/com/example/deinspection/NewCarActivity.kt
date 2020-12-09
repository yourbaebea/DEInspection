package com.example.deinspection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class NewCarActivity : AppCompatActivity() {
    var number: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_car)

        var value: String? = intent.getStringExtra("CAR_NUMBER")

        // converts the value, if the value is not int default is 0
        var aux: Int = value?.toInt() ?:  0

        this.number= aux
    }



    fun nextButton(view: View) {
        //save definitions
        setContentView(R.layout.activity_new_car_2)



    }


    fun next2Button(view: View) {
        //save definitions
        val intent = Intent(this, NewCar2Activity::class.java)
        //send only check options to set definitions
        startActivity(intent)

    }






}