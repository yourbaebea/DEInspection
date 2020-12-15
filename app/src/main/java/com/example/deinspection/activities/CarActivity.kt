package com.example.deinspection.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.deinspection.ATTRIBUTE
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import kotlinx.android.synthetic.main.activity_car.*
import kotlinx.android.synthetic.main.activity_new_car.*
import kotlinx.android.synthetic.main.activity_new_car_2.*

class CarActivity : AppCompatActivity() {
    var car = Car()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        btnHelp1.setOnClickListener() {
            helpBtn("oil")
        }
        btnInfo1.setOnClickListener() {
            infoBtn("oil")
        }
        btnUpdate1.setOnClickListener() {
           updateBtn("oil")
        }
        // missing all the other btn listeners

        /*


            FALTA ADICIONAR OS OUTROS LISTENERS AQUI


         */

        //no pop up, the only info changed in this page is the button to change the time the alarms go off
        btnBackC.setOnClickListener() {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        editCarBtn.setOnClickListener() {
            val intent = Intent(this, NewCarActivity::class.java)
            startActivity(intent)
        }
    }

    fun helpBtn(id: String){
        val intent = Intent(this, CarDetailsHelpActivity::class.java)
        intent.putExtra(ATTRIBUTE,id)
        //current attribute
        startActivity(intent)
    }

    fun infoBtn(id: String){
        val intent = Intent(this, CarDetailsInfoActivity::class.java)
        intent.putExtra(ATTRIBUTE,id)
        //current attribute
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateBtn(id: String){
        //every back button when editing should have a popup making sure
            //pop up
            //do you want to leave without saving?
            val dialog: AlertDialog = AlertDialog.Builder(this)
                    .setTitle("DEInspection")
                    .setMessage("Confirmar uma nova atualização de $id?")
                    .setPositiveButton("Sim", null)
                    .setNegativeButton("Cancelar", null)
                    .show()
            val positiveButton: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val negativeButton: Button = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

            //leave without saving
            positiveButton.setOnClickListener() {
                dialog.dismiss()
                if (id=="oil")  car.oil.updateDate()
                if (id=="tirePressure")  car.tirePressure.updateDate()
                if (id=="tires")  car.tires.updateDate()
                if (id=="airFilters")  car.airFilters.updateDate()
                if (id=="windowCleaner")  car.windowCleaner.updateDate()
                if (id=="custom")  car.custom.updateDate()
                if (id=="custom2")  car.custom2.updateDate()

                if (id=="stamp")  car.stamp.updateDateLicencePlate()
                if (id=="inspection")  car.inspection.updateDateLicencePlate()
                Toast.makeText(this@CarActivity, "$id updated", Toast.LENGTH_SHORT).show()

            }
            // save and then leave
            negativeButton.setOnClickListener() {
                dialog.dismiss()
                }
            }




    }






