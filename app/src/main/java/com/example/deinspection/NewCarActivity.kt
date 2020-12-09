package com.example.deinspection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NewCarActivity : AppCompatActivity() {
    var number: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_car)

        var value: String? = intent.getStringExtra("CAR_NUMBER")

        // converts the value, if the value is not int default is 0
        var aux: Int = value?.toInt() ?:  0

        this.number= aux
    }

    fun Next(view: View) {

        setContentView(R.layout.create_new_car)


    }


    fun seeCar(view: View) {

        // if btnFinish is clicked
        // check if value == 1, thats an error
        val intent = Intent(this, CarActivity::class.java)
        intent.putExtra(CAR_NUMBER, this.number)
        //intent.putExtra(CAR_LIST, list)
        // send the list of all the cars
        startActivity(intent)


    }


}