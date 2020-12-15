package com.example.deinspection.classes

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.util.*
import kotlin.collections.ArrayList
import java.io.Serializable

class Car: Serializable {

    /*
        on buttons related to the car or attributes

        CASE1: id is type attr1, textview1, btn1, car1

        val id : String = view.id.toString()
        val i = id[-1].toInt() //this is the number of the attribute clicked

        CASE2: id is type oil, tires, etc
        val id : String = view.id.toString()
        if(id=="oil")  ...


     */



    var brand: String = ""
    var model: String = ""
    var licenceplate: String=""
    var date: Calendar? = null

    var oil = Attribute()
    var inspection = Attribute()
    var stamp = Attribute()
    var tirePressure = Attribute()
    var tires = Attribute()
    var airFilters = Attribute()
    var windowCleaner = Attribute()
    var custom = Attribute()
    var custom2 = Attribute()

    //create all the attributes here


    @RequiresApi(Build.VERSION_CODES.O)
    fun init(b: String, m: String, l: String, d: Calendar) {
        this.brand = b
        this.model = m
        this.licenceplate = l
        this.date = d

        //get date from matricula
        //stamp and inspection are calculated from that date
        // the reminder of stamp/inspection is null, bc we always do it the same day/month every year
        licencePlate()


        //default value for last date is the creation moment
        this.oil.updateDate()
        this.tirePressure.updateDate()
        this.tires.updateDate()
        this.airFilters.updateDate()
        this.windowCleaner.updateDate()
        this.custom.updateDate()
        this.custom2.updateDate()

        //default value for value is here
        // JUST FOR TESTING ALL DEFAULT VALUES ARE 1 MEANING EVERY DAY
        this.oil.updateReminder(1)
        this.tirePressure.updateReminder(1)
        this.tires.updateReminder(1)
        this.airFilters.updateReminder(1)
        this.windowCleaner.updateReminder(1)
        this.custom.updateReminder(1)
        this.custom2.updateReminder(1)


    }

    //class attribute for each attribute oil, inspection, etc
    inner class Attribute {
        var selected: Boolean =false
        lateinit var title: String
        var checkcounter: Int = -1
        var lastdate: Calendar? = null
        var nextdate: Calendar? = null
        var reminder: Int = 0
        var listcheckdates: ArrayList<Calendar>?=null


        //when update button of attribute is clicked it should get here
        // on the page this.car.(attr clicked).updateDate()
        @RequiresApi(Build.VERSION_CODES.O)
        fun updateDate(){
            //whenever we input a new check on a attribute
            val current : Calendar = Calendar.getInstance()
            this.checkcounter +=1
            this.lastdate = current
            this.lastdate?.let { listcheckdates?.add(it) } // the first value is the creation moment
            if(checkcounter==0) setNextDate() //when defining the settings there is no reminder
        }

        //for updates on the next inspection do this.car.inspection.updateDateLicencePlate
        // this function saves the date when the inspection/stamp was made but ONLY changes the year of the next date
        // no mather when you do the inspection/stamp it will only remind you on the next inspection/stamp
        @RequiresApi(Build.VERSION_CODES.O)
        fun updateDateLicencePlate(){
            //whenever we input a new check on a attribute
            val current : Calendar = Calendar.getInstance()
            this.checkcounter +=1
            this.lastdate = current
            this.lastdate?.let { listcheckdates?.add(it) } // the first value is the creation moment
            this.nextdate?.add(Calendar.YEAR, 1)
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
            if (this.lastdate!=null) this.nextdate= this.lastdate
            this.nextdate?.add(Calendar.MONTH, months)
            this.nextdate?.add(Calendar.DAY_OF_YEAR, days)
            this.nextdate?.add(Calendar.YEAR, years)
        }


        @RequiresApi(Build.VERSION_CODES.O)
        fun countUntil(): String {
            val aux : Long = Duration.between(this.nextdate?.toInstant(), this.lastdate?.toInstant()).toDays()
            val aux2= aux.toInt()
            if (aux2==1) return "$aux day"
            return "$aux2 days"
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun updateReminder(i: Int) {
            this.reminder = i
            this.setNextDate()
        }

    }

    // only used when creating new car, for updates on the next inspection do this.car.inspection.updateDateLicencePlate()
    // gets the date from the licence plate and sets last and next inspection and stamp
    fun licencePlate(){

        var current : Calendar = Calendar.getInstance()
        var aux : Calendar? = this.date

        var years: Int= current.get(Calendar.YEAR) - (this.date?.get(Calendar.YEAR) ?: 0)

        aux?.add(Calendar.YEAR, years)

        val year= current.before(aux)
        if (year) aux?.add(Calendar.YEAR, -1)
        // if current date is before the licence plate month and day(aux) , it means the lastdate was the year before


        aux?.add(Calendar.YEAR, years)
        this.inspection.lastdate= aux
        this.stamp.lastdate= aux

        this.inspection.nextdate= this.inspection.lastdate
        this.stamp.nextdate= this.stamp.lastdate
        this.inspection.nextdate?.add(Calendar.YEAR, 1)
        this.stamp.nextdate?.add(Calendar.YEAR, 1)
    }










}