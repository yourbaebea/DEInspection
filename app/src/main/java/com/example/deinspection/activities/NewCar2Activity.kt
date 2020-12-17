package com.example.deinspection.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.deinspection.DB
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import com.example.deinspection.classes.Reminder
import com.example.deinspection.database.MyRoom
import com.example.deinspection.database.dao.CarDao
import kotlinx.android.synthetic.main.activity_new_car_2.*
import java.util.*


class NewCar2Activity : AppCompatActivity() {
    var plate = intent.extras?.get("Car plate")
    lateinit var db: MyRoom
    var car = db.carDao().getByLicense(plate as String) //plate já é uma string?
    override fun onCreate(savedInstanceState: Bundle?) {

        // idk how to do this uwu
        //this should be the global var to use and update

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car_2)

        db = Room.databaseBuilder(this,MyRoom::class.java, DB.DATABASE_NAME).allowMainThreadQueries().build()
        // if the car already exists, we fill it with the info we already have
        // FOR THE LOVE OF GOD IGNORE THIS SIMPLIFICATION
        // O CARRO NAO VAI SER SEMPRE VAZIO ELE APENAS ESTÁ VAZIO PORQUE ELE FOI DEFINIDO NA LINHA 19
        filledAlready()

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
        //save all seekbar info, dates etc
        //val car = intent.extras?.get("Car") as Car

          // not sure if we should save it in this Activity or Last one
        //we should get this from the array of cars
        //this is car number = number
        val carReminderArray = db.carReminderDao().getCarReminderfromPlate(plate as String)

        //fazer um array com as strings abaixo e indexar pelo array


        for(cr in carReminderArray) { //mudar nomes para pt
                    db.reminderDao().updateSelectedByTitle(oil.isChecked,cr.reminderId,"Oil")
                    db.reminderDao().updateSelectedByTitle(inspection.isChecked,cr.reminderId,"Inspection")
                    db.reminderDao().updateSelectedByTitle(stamp.isChecked,cr.reminderId,"Stamp")
                    db.reminderDao().updateSelectedByTitle(tirePressure.isChecked,cr.reminderId,"Tire pressure")
                    db.reminderDao().updateSelectedByTitle(tires.isChecked,cr.reminderId,"Tires")
                    db.reminderDao().updateSelectedByTitle(airFilters.isChecked,cr.reminderId,"Air filters")
                    db.reminderDao().updateSelectedByTitle(windowCleaner.isChecked,cr.reminderId,"Window cleaner")

            if (custom.text.toString() != "") { //ainda n está bem
                //verificar que o título não é nenhum dos elementos do array
                db.reminderDao().updateTitle(custom.text.toString(), cr.reminderId)
                db.reminderDao().updateSelectedByTitle(true, cr.reminderId,custom.text.toString())
            }
            else  db.reminderDao().updateSelectedByTitle(false, cr.reminderId,custom.text.toString())

            if (custom2.text.toString() != ""){ //ainda n está bem
                //verificar que o título não é nenhum dos elementos do array
                db.reminderDao().updateTitle(custom2.text.toString(), cr.reminderId)
                db.reminderDao().updateSelectedByTitle(true, cr.reminderId,custom2.text.toString())
            }
            else  db.reminderDao().updateSelectedByTitle(false, cr.reminderId,custom2.text.toString())

            val intent = Intent(this, NewCar2Activity::class.java)
            intent.putExtra("Car plate", plate as String) //might go wrong

        }

        }






    fun filledAlready(){
        val carReminderArray = db.carReminderDao().getCarReminderfromPlate(plate as String)
        for(cr in carReminderArray) {
            oil.isChecked = db.reminderDao().getByIdAndTitle(cr.reminderId,"oil").selected //ou get by title só? ver depois
            //o resto é igual,mudar onde está oil(usar o array que peço para criar em cima)
            /*
            inspection.isChecked = car.inspection.selected
            stamp.isChecked = car.stamp.selected
            tirePressure.isChecked = car.tirePressure.selected
            tires.isChecked = car.tires.selected
            airFilters.isChecked = car.airFilters.selected
            windowCleaner.isChecked = car.windowCleaner.selected

            if (car.custom.selected)
                custom.setText(car.custom.title)

            if (car.custom2.selected)
                custom.setText(car.custom2.title)
             */
        }
    }


}


