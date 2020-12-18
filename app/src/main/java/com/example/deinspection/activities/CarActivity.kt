package com.example.deinspection.activities

import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.deinspection.ATTRIBUTE
import com.example.deinspection.DB
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import com.example.deinspection.classes.Reminder
import com.example.deinspection.database.MyRoom
import kotlinx.android.synthetic.main.activity_car.*
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.deinspection.MainActivity
import com.example.deinspection.classes.CarReminder


import kotlinx.android.synthetic.main.activity_car.*
import java.sql.Struct
import java.util.*

class CarActivity : AppCompatActivity() {
    lateinit var db: MyRoom

    //vars for the alarms
    lateinit var context: Context
    lateinit var alarmManager: AlarmManager
    var hour: Int = 9 // this is from the db alarm time default (its defined on the settings page)
    var minute: Int = 9 // this is from the db alarm time default (its defined on the settings page)
    val progressBarArray = arrayOf(progress_bar1,progress_bar2,progress_bar3,progress_bar4,progress_bar5,progress_bar6,progress_bar7,progress_bar8,progress_bar9)
    var plate = intent.extras?.get("Car plate")
    val titleArray = arrayOf("Oil", "Inspection", "Stamp", "Tire pressure", "Tires", "Air filters", "Window Cleaner")
    val customNames = intent.extras?.get("Customs") as Array<*>


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        db = Room.databaseBuilder(this, MyRoom::class.java, DB.DATABASE_NAME)
            .allowMainThreadQueries().build()
        val carReminderArray = db.carReminderDao().getCarReminderfromPlate(plate as String)
        for (cr in carReminderArray) {
            var pos = 0
            for (t in titleArray) {
                setprogress(progressBarArray[pos],db.reminderDao().getByIdAndTitle(cr.reminderId,t).reminder)
                pos += 1
            }
            for (c in customNames) {
                setprogress(progressBarArray[pos],db.reminderDao().getByIdAndTitle(cr.reminderId,c as String).reminder)
                pos+=1
            }
        }

        //this function updates the selected/non selected reminders
        // if we change the duration of the reminder it only updates after the alarm or a newupdate!!!!!!!
        checkSelected()

        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


        listeners()

    }


    // this for testing the alarms
/*
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, MainActivity.Receiver::class.java)

        val pendingIntent =
            PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        var time: Long = 60000 //1 min para testes apenas!!!!!!!

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)
        Log.d("CarActivity", "Alarme enviado!")
        Toast.makeText(
                this@CarActivity,
                "Funcionalidade indisponível de momento!\nEsteja atento às nossas redes sociais para quando for lançada!\n",
                Toast.LENGTH_SHORT
        ).show()

    }
*/


    //function of all the listeners
    @RequiresApi(Build.VERSION_CODES.O)
    fun listeners() {
        btnBackC.setOnClickListener() {
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

            Toast.makeText(
                this@CarActivity,
                "Funcionalidade indisponível de momento!\nEsteja atento às nossas redes sociais para quando for lançada!\n",
                Toast.LENGTH_SHORT
            ).show()
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


    fun helpBtn(id: String) {
        //go to CarDetailsHelp
        val intent = Intent(this, CarDetailsHelpActivity::class.java)
        intent.putExtra(ATTRIBUTE, id)
        startActivity(intent)


    }

    fun infoBtn(id: String) {
        //go to CarDetailsInfo
        val intent = Intent(this, CarDetailsInfoActivity::class.java)
        intent.putExtra(ATTRIBUTE, id)
        startActivity(intent)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateBtn(id: String) {
        //every back button when editing should have a popup making sure
        //pop up
        //do you want to leave without saving?
        val rem = Reminder()
        rem.title = id
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
            var intid = 0
            when (id) {
                "oil" -> {
                    intid = 1
                    rem.updateDate()
                }
                "tirePressure" -> {
                    intid = 2
                    rem.updateDate()
                }
                "tires" -> {
                    intid = 3
                    rem.updateDate()
                }
                "airFilters" -> {
                    intid = 4
                    rem.updateDate()
                }
                "windowCleaner" -> {
                    intid = 5
                    rem.updateDate()
                }
                "custom" -> {
                    intid = 6
                    rem.updateDate()
                }
                "custom2" -> {
                    intid = 7
                    rem.updateDate()
                }
                "stamp" -> {
                    intid = 8
                    rem.updateDateLicensePlate()
                }
                "inspection" -> {
                    intid = 9
                    rem.updateDateLicensePlate()
                }
            }
            db.reminderDao().updateReminder(
                selected = rem.selected,
                title = rem.title,
                checkcounter = rem.checkcounter,
                lastdate = rem.lastdate,
                nextdate = rem.lastdate,
                reminder = rem.reminder,
                id = rem.id
            )
            //this does the update for the alarm
            updateAlarm(rem, intid)

            Toast.makeText(this@CarActivity, "$id updated", Toast.LENGTH_SHORT).show()

        }
        // save and then leave
        negativeButton.setOnClickListener() {
            dialog.dismiss()
        }

    }


    //functions of the alarm
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun updateAlarm(rem: Reminder, id: Int) {
        // im suposing that the updateReminder puts the next cal in nextdate like i had
        rem.nextdate.set(Calendar.HOUR_OF_DAY, hour)
        rem.nextdate.set(Calendar.HOUR_OF_DAY, minute)
        val time = rem.nextdate.timeInMillis
        val intent = Intent(context, MainActivity.Receiver::class.java)
        db.reminderDao().updateReminder(
            selected = rem.selected,
            title = rem.title,
            checkcounter = rem.checkcounter,
            lastdate = rem.lastdate,
            nextdate = rem.lastdate,
            reminder = rem.reminder,
            id = rem.id
        )

        val pendingIntent =
            PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (pendingIntent != null) { // if there is a pendingIntent for this id we cancel it
            alarmManager.cancel(pendingIntent)
            Log.d("CarActivity", "verificamos o alarme antes de mandar notificacao")
        }

        Log.d("CarActivity", "Alarme criado para : ${rem.nextdate}")


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun checkSelected() {


        var rem: Reminder
        val carReminderArray = db.carReminderDao().getCarReminderfromPlate(plate as String)
       for (cr in carReminderArray) {
           for (t in titleArray) {
               var id = 0
               rem = db.reminderDao().getByIdAndTitle(cr.reminderId, t)
               val intent = Intent(context, MainActivity.Receiver::class.java)
               val pendingIntent =
                       PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
               if (pendingIntent != null) { // if there is a pendingIntent for this id we cancel it
                   if (!rem.selected) alarmManager.cancel(pendingIntent)
                   Log.d("MainActivity", "verificamos o alarme antes de mandar notificacao")
               } else {
                   //if there is no pending intent and selected is true, we create a new alarm
                   if (rem.selected) updateAlarm(rem, id)
               }
               id += 1
           }
       }


    }

    
   
    fun setprogress(a : ProgressBar, b : Int){
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






