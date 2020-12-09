package com.example.deinspection


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class SpinnerActivity : AppCompatActivity() {
    /*
    // this is to make a spinner on the new car atctivity, if you figure out why this doesnt work tell me lmao
    lateinit var option: Spinner
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        option= findViewById<Spinner>(R.id.spinnerBrand)
        result = findViewById<TextView>(R.id.tv_result)

        val options = arrayOf("Ford", "Renault")
        option.adapter =ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text= "Please Select an Option"
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result.text = options.get(position)

            }



        }


    }

     */
}

