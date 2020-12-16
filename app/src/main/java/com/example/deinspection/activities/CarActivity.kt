package com.example.deinspection.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deinspection.ATTRIBUTE
import com.example.deinspection.R
import kotlinx.android.synthetic.main.activity_car.*

class CarActivity : AppCompatActivity() {
    var car : Int ? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        btnHelp1.setOnClickListener() {
            helpBtn("oil")
        }

        btnInfo1.setOnClickListener() {
            infoBtn("oil")
        }






    }

    fun helpBtn(id: String){
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

    fun infoBtn(id: String){
        //val id : String = view.id.toString()
        //val i = id[-1].toInt() //this is the number of the attribute clicked


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