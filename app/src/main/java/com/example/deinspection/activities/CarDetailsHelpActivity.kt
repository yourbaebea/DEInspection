package com.example.deinspection.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.example.deinspection.R


class CarDetailsHelpActivity : AppCompatActivity() {

    //private var progress_bar: ProgressBar = findViewById(R.id.progress_bar)
   // private var progress_text: TextView = findViewById(R.id.progress_text)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details_help)


        updateProgressBar()
    }

    private fun updateProgressBar() {
        // the value of thisdetail.reminder
        //just for tests
        var reminder: Int = 2
        var aux= ((26- reminder) *100)

      //  progress_bar.progress = aux
       // progress_text.text = "$aux%"
    }
}
