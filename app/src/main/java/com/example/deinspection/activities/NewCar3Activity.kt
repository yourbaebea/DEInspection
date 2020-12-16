package com.example.deinspection.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.deinspection.ATTRIBUTE
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import kotlinx.android.synthetic.main.activity_new_car_2.*
import kotlinx.android.synthetic.main.activity_new_car_3.*

class NewCar3Activity : AppCompatActivity() {
    var car= Car()

    lateinit var slider : SeekBar
    lateinit var valor : TextView
    lateinit var slider2 : SeekBar
    lateinit var valor2 : TextView
    lateinit var slider3 : SeekBar
    lateinit var valor3 : TextView
    lateinit var slider4 : SeekBar
    lateinit var valor4 : TextView
    lateinit var slider5 : SeekBar
    lateinit var valor5 : TextView
    lateinit var slider6 : SeekBar
    lateinit var valor6 : TextView
    lateinit var slider7 : SeekBar
    lateinit var valor7 : TextView
    lateinit var slider8 : SeekBar
    lateinit var valor8 : TextView
    lateinit var slider9 : SeekBar
    lateinit var valor9 : TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car_3)

        seekBar.min = 1
        seekBar.max = 26
        seekBar2.min = 1
        seekBar2.max = 26
        seekBar3.min = 1
        seekBar3.max = 26
        seekBar4.min = 1
        seekBar4.max = 26
        seekBar5.min = 1
        seekBar5.max = 26
        seekBar6.min = 1
        seekBar6.max = 26
        seekBar7.min = 1
        seekBar7.max = 26
        seekBar8.min = 1
        seekBar8.max = 26
        seekBar9.min = 1
        seekBar9.max = 26

        slider = findViewById(R.id.seekBar) as SeekBar
        valor = findViewById(R.id.attribute1) as TextView
        slider2 = findViewById(R.id.seekBar2) as SeekBar
        valor2 = findViewById(R.id.attribute2) as TextView
        slider3 = findViewById(R.id.seekBar3) as SeekBar
        valor3 = findViewById(R.id.attribute3) as TextView
        slider4 = findViewById(R.id.seekBar4) as SeekBar
        valor4 = findViewById(R.id.attribute4) as TextView
        slider5 = findViewById(R.id.seekBar5) as SeekBar
        valor5 = findViewById(R.id.attribute5) as TextView
        slider6 = findViewById(R.id.seekBar6) as SeekBar
        valor6 = findViewById(R.id.attribute6) as TextView
        slider7 = findViewById(R.id.seekBar7) as SeekBar
        valor7 = findViewById(R.id.attribute7) as TextView
        slider8 = findViewById(R.id.seekBar8) as SeekBar
        valor8 = findViewById(R.id.attribute8) as TextView
        slider9 = findViewById(R.id.seekBar9) as SeekBar
        valor9 = findViewById(R.id.attribute9) as TextView

        // if the car already exists, we fill it with the info we already have
        // FOR THE LOVE OF GOD IGNORE THIS SIMPLIFICATION
        // O CARRO NAO VAI SER SEMPRE VAZIO ELE APENAS ESTÁ VAZIO PORQUE ELE FOI DEFINIDO NA LINHA 19
        if (car != null) filledAlready()

        seekBar(slider, valor)
        seekBar(slider2, valor2)
        seekBar(slider3, valor3)
        seekBar(slider4, valor4)
        seekBar(slider5, valor5)
        seekBar(slider6, valor6)
        seekBar(slider7, valor7)
        seekBar(slider8, valor8)
        seekBar(slider9, valor9)

        btnBackAL.setOnClickListener() {

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
                Toast.makeText(this@NewCar3Activity, "Saving info", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                goBack()
            }
            //cancel and continue on this page
            neutralButton.setOnClickListener() {
                dialog.dismiss()
            }
        }


        btnNext3.setOnClickListener() {
            saveInfo()

            var number = 1

            val intent = Intent(this, CarActivity::class.java)
            intent.putExtra(ATTRIBUTE, number)
            //intent.putExtra(CAR_LIST, list)
            // send the list of all the cars
            startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun filledAlready() {

        seekBar.progress= car.oil.reminder
        seekBar2.progress= car.inspection.reminder
        seekBar3.progress= car.stamp.reminder
        seekBar4.progress= car.tirePressure.reminder
        seekBar5.progress= car.tires.reminder
        seekBar6.progress = car.airFilters.reminder
        seekBar7.progress = car.windowCleaner.reminder
        seekBar8.progress = car.custom.reminder
        seekBar9.progress = car.custom2.reminder

        seekBar.progress= 10
        seekBar2.progress= 1
    }

    //save all the info in this page
    fun saveInfo(){
        car.oil.reminder= seekBar.progress
        car.inspection.reminder = seekBar2.progress
        car.stamp.reminder =  seekBar3.progress
        car.tirePressure.reminder= seekBar4.progress
        car.tires.reminder= seekBar5.progress
        car.airFilters.reminder = seekBar6.progress
        car.windowCleaner.reminder = seekBar7.progress
        car.custom.reminder= seekBar8.progress
        car.custom2.reminder = seekBar9.progress
    }

    //where the back button goes to
    fun goBack(){
        val intent = Intent(this, NewCar2Activity::class.java)
        startActivity(intent)
    }

    fun editTV(value: Int): String {
        val aux: Int
        var str: String =""
        //"REMINDE ME "
        when (value) {
            0 -> str ="never" //0
            1 -> str ="every day" //1
            in 2..6 -> str ="every @value days" //1-6days
            in 7..10 -> {
                aux = 7 * (value - 6) //1-4 weeks
                str = if (aux== 1) "every week"
                else "every @aux weeks"
            }
            //in 100..Int.MAX_REMINDER //1-12 months
            in 11..21 -> {
                aux = (value - 10) //1-11 months
                str = if (aux== 1) "every month"
                else "every @aux months"
            }
            in 22..26 -> {
                aux = (value - 21) // 1-5 years
                str = if (aux== 1) "every year"
                else "every @aux years"
            }
            else -> println("something went wrong value is out of bounds")
        }
        return str
    }

    fun seekBar(a : SeekBar, b : TextView){
        a.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                b.text = editTV(a.progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }




}

