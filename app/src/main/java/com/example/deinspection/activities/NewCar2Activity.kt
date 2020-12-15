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
import kotlinx.android.synthetic.main.activity_new_car.*
import kotlinx.android.synthetic.main.activity_new_car_2.*
import java.util.*


class NewCar2Activity : AppCompatActivity() {
    var car = Car()
    override fun onCreate(savedInstanceState: Bundle?) {

        // idk how to do this uwu
        //this should be the global var to use and update

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car_2)

        // if the car already exists, we fill it with the info we already have
        // FOR THE LOVE OF GOD IGNORE THIS SIMPLIFICATION
        // O CARRO NAO VAI SER SEMPRE VAZIO ELE APENAS ESTÁ VAZIO PORQUE ELE FOI DEFINIDO NA LINHA 19
        if (car !=null) filledAlready()

        //every back button when editing should have a popup making sure
        btnBackNC2.setOnClickListener() {
            //pop up
            //do you want to leave without saving?
            val dialog: AlertDialog = AlertDialog.Builder(this)
                    .setTitle("DEInspection")
                    .setMessage("Pretende sair sem guardar?")
                    .setPositiveButton("Sair", null)
                    .setNegativeButton("Guardar e sair", null)
                    .setNeutralButton("Cancelar", null)
                    .show()
            val positiveButton: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val negativeButton: Button = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            val neutralButton: Button = dialog.getButton(AlertDialog.BUTTON_NEUTRAL)

            //leave without saving
            positiveButton.setOnClickListener() {
                    dialog.dismiss()
                    goBack()
            }
            // save and then leave
            negativeButton.setOnClickListener() {
                    //save info
                    saveInfo()
                    Toast.makeText(this@NewCar2Activity, "Saving info", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    goBack()
            }
            //cancel and continue on this page
            neutralButton.setOnClickListener() {
                    dialog.dismiss()
            }
        }


        btnNext2.setOnClickListener(){
            //save definitions
            saveInfo()

            val intent = Intent(this, NewCar3Activity::class.java)
            //send only check options to set definitions
            startActivity(intent)
        }

    }


    //where the back button goes to
    private fun goBack(){
        //save definitions
        val intent = Intent(this, NewCarActivity::class.java)
        //send only check options to set definitions
        startActivity(intent)
    }

    //save all the info in this page
    fun saveInfo(){

        car.oil.selected = oil.isChecked
        car.inspection.selected= inspection.isChecked
        car.stamp.selected= stamp.isChecked
        car.tirePressure.selected= tirePressure.isChecked
        car.tires.selected= tires.isChecked
        car.airFilters.selected= airFilters.isChecked
        car.windowCleaner.selected= windowCleaner.isChecked

        if (custom.text.toString() != ""){
            car.custom.title = custom.text.toString()
            car.custom.selected = true
        }
        else car.custom.selected = false

        if (custom2.text.toString() != ""){
            car.custom2.title = custom.text.toString()
            car.custom2.selected = true
        }
        else car.custom2.selected = false
    }


    fun filledAlready(){
        oil.isChecked =car.oil.selected
        inspection.isChecked = car.inspection.selected
        stamp.isChecked= car.stamp.selected
        tirePressure.isChecked= car.tirePressure.selected
        tires.isChecked = car.tires.selected
        airFilters.isChecked = car.airFilters.selected
        windowCleaner.isChecked= car.windowCleaner.selected

        if (car.custom.selected)
            custom.setText(car.custom.title)

        if (car.custom2.selected)
            custom.setText(car.custom2.title)

    }


}


