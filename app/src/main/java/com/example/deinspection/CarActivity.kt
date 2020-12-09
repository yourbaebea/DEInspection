package com.example.deinspection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CarActivity : AppCompatActivity() {
    var car : Int ? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)


    }

    //val brands = arrayOf("Renault", "Ford");
    //val arrayAdapter = ArrayAdapter(this , R.layout.support_simple_spinner_dropdown_item, brands)
    //spinner2.adapter
    //we should do this by getting the info from a file, this is just temporary




}