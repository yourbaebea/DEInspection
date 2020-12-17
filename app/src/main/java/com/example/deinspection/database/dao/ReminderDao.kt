package com.example.deinspection.database.dao;


import androidx.room.*
import com.example.deinspection.classes.Car

import com.example.deinspection.classes.Reminder;
import java.util.*

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder WHERE id = :id")
    fun getById(id:Int):Reminder
    @Query("SELECT * FROM reminder WHERE id= :id and title= :title")
    fun getByIdAndTitle(id:Int,title: String):Reminder
    @Query ("UPDATE reminder SET nextdate = :nextdate WHERE id= :id ")
    fun updateNextDate(nextdate: Calendar,id: Int)
    @Query("UPDATE reminder SET selected = :selected WHERE id = :id")
    fun updateSelected(selected: Boolean,id: Int)
    @Query("UPDATE reminder SET selected = :selected WHERE id = :id and title = :title")
    fun updateSelectedByTitle(selected: Boolean,id: Int,title: String)
    @Query("UPDATE reminder SET title = :title WHERE id = :id")
    fun updateTitle(title: String,id: Int)
    @Query("UPDATE reminder SET title = :title WHERE id = :id and title = :oldtitle")
    fun updateTitlebyTitle(title: String,id: Int,oldtitle: String)
    @Query("UPDATE reminder SET reminder=:reminder WHERE id= :id and title= :title")
    fun updateReminderByTitle(reminder: Int,id: Int,title: String)
    @Query("SELECT * FROM reminder WHERE id IN(SELECT reminderId FROM car_reminder WHERE licenseplate =:licenseplate)  ")
    fun getReminderFromLicense(id:Int,licenseplate:String):List<Reminder>
    @Query("UPDATE reminder SET selected = :selected,title = :title,checkcounter = :checkcounter,lastdate = :lastdate,nextdate = :nextdate,reminder =:reminder WHERE id= :id ")
    fun updateReminder(selected:Boolean,title:String,checkcounter:Int,lastdate:Calendar,nextdate: Calendar,reminder : Int,id:Int)
    @Insert
    fun insertReminder(vararg r: Reminder):List<Long> //lista de ids
}
