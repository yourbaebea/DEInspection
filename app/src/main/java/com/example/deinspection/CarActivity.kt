package com.example.deinspection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CarActivity : AppCompatActivity() {
    var car : Int ? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)


    }

    fun updateBtn(view: View){
        // check the attribute related to the click
        // and update that
        //car.attribute.updateDate()


    }

    fun helpBtn(view: View){
        // check the attribute related to the click

        //go to CarDetailsHelp
        val intent = Intent(this, CarDetailsHelpActivity::class.java)

        // save the car and the attribute
        // var car = 1, attr="oil"
        //something like that

        startActivity(intent)


    }
}