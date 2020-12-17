package com.example.deinspection.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.deinspection.ATTRIBUTE
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import kotlinx.android.synthetic.main.activity_car.*


class CarActivity : AppCompatActivity() {
    var car = Car()

    /*lateinit var a1 : TextView
    lateinit var a2 : TextView
    lateinit var a3 : TextView
    lateinit var a4 : TextView
    lateinit var a5 : TextView
    lateinit var a6 : TextView
    lateinit var a7 : TextView
    lateinit var a8 : TextView
    lateinit var a9 : TextView*/

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        /*a1 = findViewById(R.id.textView1)
        a2 = findViewById(R.id.textView2)
        a3 = findViewById(R.id.textView3)
        a4 = findViewById(R.id.textView4)
        a5 = findViewById(R.id.textView5)
        a6 = findViewById(R.id.textView6)
        a7 = findViewById(R.id.textView)
        a8 = findViewById(R.id.textView8)
        a9 = findViewById(R.id.textView9)*/

        /*setprogress(progress_bar1, a1)
        setprogress(progress_bar2, a2)
        setprogress(progress_bar3, a3)
        setprogress(progress_bar4, a4)
        setprogress(progress_bar5, a5)
        setprogress(progress_bar6, a6)
        setprogress(progress_bar7, a7)
        setprogress(progress_bar8, a8)
        setprogress(progress_bar9, a9)*/

        /*setprogress(progress_bar1, car.inspection.reminder)
        setprogress(progress_bar2, car.stamp.reminder)
        setprogress(progress_bar3, car.oil.reminder)
        setprogress(progress_bar4, car.tirePressure.reminder)
        setprogress(progress_bar5, car.tires.reminder)
        setprogress(progress_bar6, car.airFilters.reminder)
        setprogress(progress_bar7, car.windowCleaner.reminder)
        setprogress(progress_bar8, car.custom.reminder)
        setprogress(progress_bar9, car.custom2.reminder)*/

        btnBackC.setOnClickListener(){
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        btnEditCar.setOnClickListener() {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        btnInspection.setOnClickListener() {
            //val intent = Intent(this, InspectionActivity::class.java)
            //startActivity(intent)

            Toast.makeText(this@CarActivity, "this feat. is not done", Toast.LENGTH_SHORT).show()
        }


        // listeners for all the other buttons

        btnHelp1.setOnClickListener() {
            helpBtn("inspection")
        }

        btnInfo1.setOnClickListener() {
            infoBtn("inspection")
        }

        btnUpdate1.setOnClickListener() {
            updateBtn("inspection")
        }

        btnHelp2.setOnClickListener() {
            helpBtn("stamp")
        }

        btnInfo2.setOnClickListener() {
            infoBtn("stamp")
        }

        btnUpdate2.setOnClickListener() {
            updateBtn("stamp")
        }

        btnHelp3.setOnClickListener() {
            helpBtn("oil")
        }

        btnInfo3.setOnClickListener() {
            infoBtn("oil")
        }

        btnUpdate3.setOnClickListener() {
            updateBtn("oil")
        }

        btnHelp4.setOnClickListener() {
            helpBtn("tirePressure")
        }

        btnInfo4.setOnClickListener() {
            infoBtn("tirePressure")
        }

        btnUpdate4.setOnClickListener() {
            updateBtn("tirePressure")
        }

        btnHelp5.setOnClickListener() {
            helpBtn("tires")
        }

        btnInfo5.setOnClickListener() {
            infoBtn("tires")
        }

        btnUpdate5.setOnClickListener() {
            updateBtn("tires")
        }

        btnHelp6.setOnClickListener() {
            helpBtn("airFilters")
        }

        btnInfo6.setOnClickListener() {
            infoBtn("airFilters")
        }

        btnUpdate6.setOnClickListener() {
            updateBtn("airFilters")
        }

        btnHelp7.setOnClickListener() {
            helpBtn("windowCleaner")
        }

        btnInfo7.setOnClickListener() {
            infoBtn("windowCleaner")
        }

        btnUpdate7.setOnClickListener() {
            updateBtn("windowCleaner")
        }

        btnHelp8.setOnClickListener() {
            helpBtn("custom")
        }

        btnInfo8.setOnClickListener() {
            infoBtn("custom")
        }

        btnUpdate8.setOnClickListener() {
            updateBtn("custom")
        }

        btnHelp9.setOnClickListener() {
            helpBtn("custom2")
        }

        btnInfo9.setOnClickListener() {
            infoBtn("custom2")
        }

        btnUpdate9.setOnClickListener() {
            updateBtn("custom2")
        }
    }

    fun helpBtn(id: String){
        //go to CarDetailsHelp
        val intent = Intent(this, CarDetailsHelpActivity::class.java)
        intent.putExtra(ATTRIBUTE, id)
        startActivity(intent)


    }

    fun infoBtn(id: String){
        //go to CarDetailsInfo
        val intent = Intent(this, CarDetailsInfoActivity::class.java)
        intent.putExtra(ATTRIBUTE, id)
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

    fun setprogress(a : ProgressBar, b : Int){
        a.progress = car.oil.reminder
        when (b){
            0 -> a.progress=0 //never
            1 -> a.progress=100 //everyday
            in 2..6 -> {
                a.progress=100/b
            } //1-6days
            in 7..10 -> a.progress=100/(7*(b-6)) //1-4 weeks
            in 11..21 -> a.progress=100/(31*(b-10)) //1-11 months
            in 22..26 -> a.progress=100/(365*(b-21)) //1-5 years
        }
    }

}



