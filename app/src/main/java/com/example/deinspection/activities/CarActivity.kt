package com.example.deinspection.activities

import android.view.View
import android.widget.Button
import android.widget.Toast
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


import kotlinx.android.synthetic.main.activity_car.*
import java.util.*

class CarActivity : AppCompatActivity() {
    lateinit var db: MyRoom

    //vars for the alarms
    lateinit var context: Context
    lateinit var alarmManager: AlarmManager
    var hour: Int = 9 // this is from the db alarm time default (its defined on the settings page)
    var minute: Int = 9 // this is from the db alarm time default (its defined on the settings page)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        db = Room.databaseBuilder(this, MyRoom::class.java, DB.DATABASE_NAME)
            .allowMainThreadQueries().build()


        //this function updates the selected/non selected reminders
        // if we change the duration of the reminder it only updates after the alarm or a newupdate!!!!!!!
        checkSelected()

        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


        listeners()

    }

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
        var time = rem.nextdate.timeInMillis
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
        /*
        this is the correct way to do it but for testing we are going to set time 1min
        AFTER TESTING DELETE THIS
         */

        time = 60000 //1 min para testes apenas!!!!!!!

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun checkSelected() {
        var rem: Reminder
        for (i in 1..9) {
            rem = getRem(i)
            val intent = Intent(context, MainActivity.Receiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(context, i, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if (pendingIntent != null) { // if there is a pendingIntent for this id we cancel it
                if (!rem.selected) alarmManager.cancel(pendingIntent)
                Log.d("MainActivity", "verificamos o alarme antes de mandar notificacao")
            } else {
                //if there is no pending intent and selected is true, we create a new alarm
                if (rem.selected) updateAlarm(rem, i)
            }
        }


    }

    /*
    ROQUE O QUE É ESTE ID?????
     */
    fun getRem(n: Int): Reminder {
        when (n) {
            //o que é suposto ser este id???
            1 -> return db.reminderDao().getByIdAndTitle(1, "oil")
            2 -> return db.reminderDao().getByIdAndTitle(1, "tirePressure")
            3 -> return db.reminderDao().getByIdAndTitle(1, "tires")
            4 -> return db.reminderDao().getByIdAndTitle(1, "airFilters")
            5 -> return db.reminderDao().getByIdAndTitle(1, "windowCleaner")
            6 -> return db.reminderDao().getByIdAndTitle(1, "custom")
            7 -> return db.reminderDao().getByIdAndTitle(1, "custom2")
            8 -> return db.reminderDao().getByIdAndTitle(1, "stamp")
            9 -> return db.reminderDao().getByIdAndTitle(1, "inspection")
        }
        return Reminder() //empty reminder

    }



}






