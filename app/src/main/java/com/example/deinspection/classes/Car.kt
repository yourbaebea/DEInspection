package com.example.deinspection.classes

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.util.*
import kotlin.collections.ArrayList


class Car {

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
    var attr = ArrayList<Attribute>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun init(b: String, m: String, d: Calendar) {
        this.brand = b
        this.model = m
        this.date = d
        this.oil.updateDate()
        this.inspection.updateDate()
        this.stamp.updateDate()
        this.tirePressure.updateDate()
        this.tires.updateDate()
        this.airFilters.updateDate()
        this.windowCleaner.updateDate()
        this.custom.updateDate()
        this.custom2.updateDate()

    }


    //this is a button listener for when an attribute is updated
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateReminder(n: String, i: Int) {
        var aux = Attribute()
        if (n == "oil") aux= this.oil
        if (n == "inspection") aux= this.inspection
        if (n == "stamp") aux= this.stamp
        if (n == "tirePressure") aux= this.tirePressure
        if (n == "tires") aux= this.tires
        if (n == "airFilters") aux= this.airFilters
        if (n == "windowCleaner") aux= this.windowCleaner
        if (n == "custom") aux= this.custom
        if (n == "custom2") aux= this.custom2
        aux.reminder = i
        aux.setNextDate()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun updateCheck(n:String){
        var aux = Attribute()
        if (n == "oil") aux= this.oil
        if (n == "inspection") aux= this.inspection
        if (n == "stamp") aux= this.stamp
        if (n == "tirePressure") aux= this.tirePressure
        if (n == "tires") aux= this.tires
        if (n == "airFilters") aux= this.airFilters
        if (n == "windowCleaner") aux= this.windowCleaner
        if (n == "custom") aux= this.custom
        if (n == "custom2") aux= this.custom2
        aux.updateDate()
    }

    inner class Attribute {
        var selected: Boolean =false
        var checkcounter: Int = 0
        var lastdate: Calendar? = null
        var nextdate: Calendar? = null
        var reminder: Int = 0
        var listcheckdates: ArrayList<Calendar>?=null



        @RequiresApi(Build.VERSION_CODES.O)
        fun updateDate(){
            //whenever we input a new check on a attribute

            val current : Calendar = Calendar.getInstance()
            this.checkcounter +=1
            this.lastdate = current
            this.lastdate?.let { listcheckdates?.add(it) }
            if (reminder!=0) setNextDate() //when defining the settings, there is no reminder so it doesnt have a reminder
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

            //get the last date (its right now) and adds the amount of time of the reminder
            this.nextdate= this.lastdate

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
    }

}