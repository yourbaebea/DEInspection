
package com.example.deinspection.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deinspection.MainActivity.Companion.carList
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import kotlinx.android.synthetic.main.activity_new_car.*
import java.io.Serializable
import java.util.*


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

        val marca = editCarBrand.text.toString()
        val modelo = editCarModel.text.toString()
        val data = editTextDate.text.toString()
        val matricula = editTextMatricula.text.toString()//

        setContentView(R.layout.activity_new_car_2)
        val car = Car()
        car.brand = marca
        car.model = modelo
        car.date = dateParse(data)
        //car.stamp = matricula // how the **** do I turn this shit into an inner class object like that one
        val intent = Intent(this@NewCarActivity,NewCar2Activity::class.java)
        intent.putExtra("Car",car)

        carList.add(car)

    }


    fun next2Button(view: View) {
        //save definitions
        val intent = Intent(this, NewCar2Activity::class.java)
        //send only check options to set definitions
        startActivity(intent)

    }

    fun dateParse(date: String): Date {
        /* Date parser by / or - spliter that work with both dd/MM/yyyy and yyyy/MM/dd */
        val parsed = date.split('/','-')
        var year = 0
        val month = parsed[1].toInt()
        var day = 0
        for(number in parsed){
            var count = 1
            if (number.length == 4) {
                year = number.toInt()
                if (count == 1) {

                    day =   parsed[2].toInt()
                }
                else {

                    day =   parsed[0].toInt()
                }


            }
            count += 1
        }
        return Date(year,month,day)
    }

}