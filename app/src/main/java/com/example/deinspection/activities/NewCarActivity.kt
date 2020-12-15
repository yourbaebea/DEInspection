
package com.example.deinspection.activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import kotlinx.android.synthetic.main.activity_new_car.*
import java.text.SimpleDateFormat
import java.util.*


class NewCarActivity : AppCompatActivity() {
    var number: Int=0
    var car = Car()
    var accepted= false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_car)


        // if the car already exists, we fill it with the info we already have
        // FOR THE LOVE OF GOD IGNORE THIS SIMPLIFICATION
        // O CARRO NAO VAI SER SEMPRE VAZIO ELE APENAS ESTÁ VAZIO PORQUE ELE FOI DEFINIDO NA LINHA 24
        if (car !=null) filledAlready()

        //every back button when editing should have a popup making sure
        btnBackNC.setOnClickListener() {
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
                accepted= saveInfo(car)
                if (accepted) {
                    Toast.makeText(this@NewCarActivity, "Saving info", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    goBack()
                }
                else {
                    Toast.makeText(this@NewCarActivity, "Info is not acceptable, pls try again", Toast.LENGTH_SHORT).show()
                }
            }
            //cancel and continue on this page
            neutralButton.setOnClickListener() {
                dialog.dismiss()
            }
        }

        btnNext.setOnClickListener() {
            //save definitions
            accepted= saveInfo(car)
            if (accepted) {
                val intent = Intent(this, NewCar2Activity::class.java)
                //send only check options to set definitions
                startActivity(intent)
            }
            else {
                Toast.makeText(this@NewCarActivity, "Info is not acceptable, pls try again", Toast.LENGTH_SHORT).show()
            }
        }




    }


    //where the back button goes to
    private fun goBack(){
        //save definitions
        val intent = Intent(this, NewCarActivity::class.java)

        startActivity(intent)
    }

    // new car is created here if all is good
    //save all the info in this page
    @RequiresApi(Build.VERSION_CODES.O)
    fun saveInfo(car: Car): Boolean{


        val marca = editCarBrand.text.toString()
        val modelo = editCarModel.text.toString()
        val mes = editMonth.text.toString().toInt()
        val ano= editYear.text.toString().toInt()
        val matricula = editTextMatricula.text.toString()

        val confirmation= matricula.chunked(2)

        //accepts all licences with the format
        // AAAA00 AA00AA 00AAAA AA0000 00AA00 0000AA
        if (confirmation.size == 3) {
            var countn=0
            var countl=0
            for( n in confirmation) {
                if (n[0].isDigit() && n[1].isDigit()) countn += 1
                if (n[0].isLetter() && n[1].isLetter()) countl += 1
            }
            if( countl ==0 || countn==0) return false

        }
        else return false


        when (ano) {!in 1900..2022 -> return false}
        when (mes) {!in 1..12 -> return false}

        var data: Calendar = Calendar.getInstance()
        data.set(Calendar.YEAR, ano)
        data.set(Calendar.MONTH, mes)
        data.set(Calendar.DAY_OF_MONTH, 1)


        car.init(marca,modelo,matricula,data)
        return true
    }


    fun filledAlready(){
        editCarBrand.setText(car.brand)
        editCarModel.setText(car.model)
        editTextMatricula.setText(car.licenceplate)
        car.date?.let { editMonth.setText(it.get(Calendar.MONTH)) }
        car.date?.let { editYear.setText(it.get(Calendar.YEAR)) }

    }


}



