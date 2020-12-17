package com.example.deinspection.classes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.time.Duration
import java.util.*
import kotlin.collections.ArrayList

@Entity(
        tableName = "reminder"
)
class Reminder(
        @PrimaryKey(autoGenerate = true) val id  : Int = 0,
        var selected: Boolean= false,
        @ColumnInfo() var title: String= "",
        var checkcounter: Int = -1,
        var lastdate: Calendar = Calendar.getInstance(),
        var nextdate: Calendar = Calendar.getInstance(),
        var reminder: Int = 0,
) {
    //when update button of attribute is clicked it should get here
    // on the page this.car.(attr clicked).updateDate()
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateDate(){
        //whenever we input a new check on a attribute
        val current : Calendar = Calendar.getInstance()
        this.checkcounter +=1
        this.lastdate = current
        if(checkcounter==0) setNextDate() //when defining the settings there is no reminder
    }

    //for updates on the next inspection do this.car.inspection.updateDateLicencePlate
    // this function saves the date when the inspection/stamp was made but ONLY changes the year of the next date
    // no mather when you do the inspection/stamp it will only remind you on the next inspection/stamp
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateDateLicensePlate(){
        //whenever we input a new check on a attribute
        val current : Calendar = Calendar.getInstance()
        this.checkcounter +=1
        this.lastdate = current
        this.nextdate.add(Calendar.YEAR, 1)
    }

    // this function is also to show text in definitions of the attributes on newcar2activity
    @RequiresApi(Build.VERSION_CODES.O)
    fun setNextDate(){
        var days: Int =0
        var months: Int =0
        var years: Int =0
        when(this.reminder) {
            in 0..6 -> days = this.reminder //1-6days
            in 7..10 -> days = 7 * (this.reminder - 6) //1-4 weeks
            //in 100..Int.MAX_REMINDER //1-12 months
            in 11..21 -> months = (this.reminder - 10) //1-11 months
            in 22..26 -> years = (this.reminder - 21) // 1-5 years
            else -> println("something went wrong value is out of bounds")
        }

        //get the last date and adds the amount of time of the reminder
        this.nextdate= this.lastdate
        this.nextdate.add(Calendar.MONTH, months)
        this.nextdate.add(Calendar.DAY_OF_YEAR, days)
        this.nextdate.add(Calendar.YEAR, years)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun countUntil(): String {
        val aux : Long = Duration.between(this.nextdate.toInstant(), this.lastdate.toInstant()).toDays()
        val aux2= aux.toInt()
        if (aux2==1) return "$aux day"
        return "$aux2 days"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateReminder(i: Int) {
        this.reminder = i
        this.setNextDate()
    }


    /* WILL I EVER NEED THIS ?
    fun LicensePlate(car: Car,){

        var current : Calendar = Calendar.getInstance()
        var aux : Calendar? = car.date

        var years: Int= current.get(Calendar.YEAR) - (car.date?.get(Calendar.YEAR) ?: 0)

        aux?.add(Calendar.YEAR, years)

        val year= current.before(aux)
        if (year) aux?.add(Calendar.YEAR, -1)
        // if current date is before the licence plate month and day(aux) , it means the lastdate was the year before


        aux?.add(Calendar.YEAR, years)
        this.lastdate= aux

        this.nextdate= this.lastdate
        this.nextdate?.add(Calendar.YEAR, 1)
    }
    */
}