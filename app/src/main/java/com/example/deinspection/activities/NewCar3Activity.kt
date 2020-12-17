package com.example.deinspection.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.deinspection.ATTRIBUTE
import com.example.deinspection.DB
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import com.example.deinspection.database.MyRoom
import kotlinx.android.synthetic.main.activity_new_car_2.*
import kotlinx.android.synthetic.main.activity_new_car_3.*

class NewCar3Activity : AppCompatActivity() {
    var plate = intent.extras?.get("Car plate")
    lateinit var db: MyRoom
    var car = db.carDao().getByLicense(plate as String) //plate já é uma string?
    val titleArray = arrayOf("Oil", "Inspection", "Stamp", "Tire pressure", "Tires", "Air filters", "Window Cleaner")
    val customArray = intent.extras?.get("Customs") as Array<*>
    val seekbarArray = arrayOf(seekBar,seekBar2,seekBar3,seekBar4,seekBar5,seekBar6,seekBar7,seekBar8,seekBar9)



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
    val sliderArray = arrayOf(slider,slider2,slider3,slider4,slider5,slider6,slider7,slider8,slider9)
    val valorArray = arrayOf(valor,valor2,valor3,valor4,valor5,valor6,valor7,valor8,valor9)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car_3)

        db = Room.databaseBuilder(this, MyRoom::class.java, DB.DATABASE_NAME).allowMainThreadQueries().build()

        val rSeekBarArray = arrayOf(R.id.seekBar,R.id.seekBar2,R.id.seekBar3,R.id.seekBar4,R.id.seekBar5,R.id.seekBar6,R.id.seekBar7,R.id.seekBar8,R.id.seekBar9)
        val attributeArray = arrayOf(R.id.attribute1,R.id.attribute2,R.id.attribute3,R.id.attribute4,R.id.attribute5,R.id.attribute6,R.id.attribute7,R.id.attribute8,R.id.attribute9)

        for (bar in seekbarArray){
            bar.min = 1
            bar.max = 26
        }

        var pos= 0
        for(sb in sliderArray) {
            sliderArray[pos] = findViewById<SeekBar>(rSeekBarArray[pos])
            valorArray[pos] = findViewById<TextView>(attributeArray[pos])
            pos+= 1
        }


        // if the car already exists, we fill it with the info we already have
        // FOR THE LOVE OF GOD IGNORE THIS SIMPLIFICATION
        // O CARRO NAO VAI SER SEMPRE VAZIO ELE APENAS ESTÁ VAZIO PORQUE ELE FOI DEFINIDO NA LINHA 19
        filledAlready()
        pos=0
        for(sb in sliderArray) {
            seekBar(sb,valorArray[pos])
            pos+=1
        }


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
                Toast.makeText(this@NewCar3Activity, "Saving info", Toast.LENGTH_LONG).show()
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

        val carReminderArray = db.carReminderDao().getCarReminderfromPlate(plate as String)
        for(cr in carReminderArray) {
            var pos = 0
            for (t in titleArray) {
                seekbarArray[pos].progress = db.reminderDao().getByIdAndTitle(cr.reminderId, t).reminder
                pos+= 1
            }
            var cpos = 0
            for (c in customArray){
                seekbarArray[pos].progress = db.reminderDao().getByIdAndTitle(cr.reminderId, c as String).reminder
                pos+= 1
                cpos+= 1
            }
        }


        seekBar.progress= 10
        seekBar2.progress= 1
    }

    //save all the info in this page
    fun saveInfo(){
        val carReminderArray = db.carReminderDao().getCarReminderfromPlate(plate as String)
        for(cr in carReminderArray) {
            var pos = 0
            for (t in titleArray) {
                db.reminderDao().updateReminderByTitle(seekbarArray[pos].progress, cr.reminderId, t)
                pos += 1
            }
            for (c in customArray){
                db.reminderDao().updateReminderByTitle(seekbarArray[pos].progress, cr.reminderId, c as String)
                pos+= 1
            }
        }
    }

    //where the back button goes to
    fun goBack(){
        val intent = Intent(this, NewCar2Activity::class.java)
        startActivity(intent)
    }

   fun editTV(value: Int, ola: TextView): String {
        val aux: Int
        var str: String = ola.text as String
        //"REMINDE ME "
        when (value) {
            0 -> str +=": never" //0
            1 -> str +=": every day" //1
            in 2..6 -> str +=": every "+ value + " days" //1-6days
            in 7..10 -> {
                aux = 7 * (value - 6) //1-4 weeks
                str += if (aux== 1) ": every week"
                else ": every " + aux/7 + " weeks"
            }
            //in 100..Int.MAX_REMINDER //1-12 months
            in 11..21 -> {
                aux = (value - 10) //1-11 months
                str += if (aux== 1) ": every month"
                else ": every " + aux + " months"
            }
            in 22..26 -> {
                aux = (value - 21) // 1-5 years
                str += if (aux== 1) ": every year"
                else ": every " + aux + " years"
            }
            else -> println("something went wrong value is out of bounds")
        }
        return str
    }

    fun seekBar(a : SeekBar, b : TextView){
        a.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                b.text = editTV(a.progress,b)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }




}

