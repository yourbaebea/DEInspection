package com.example.deinspection.activities


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import kotlinx.android.synthetic.main.activity_more_info.*
import java.util.*


class MoreAboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        btnBackAL.setOnClickListener() {
            val intent = Intent(this, SettingsActivity::class.java)

            // save the car and the attribute
            // var car = 1, attr="oil"
            //something like that

            startActivity(intent)
        }

    }
}
