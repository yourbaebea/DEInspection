package com.example.deinspection.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.deinspection.classes.CarReminder

@Dao
interface CarReminderDao {
    @Query("Select * from car_reminder where licenseplate = :licenseplate")
    fun getCarReminderfromPlate(licenseplate:String):List<CarReminder>
    @Query("Select * from car_reminder where reminderId = :reminderId")
    fun getCarReminderfromId(reminderId: Int):List<CarReminder>

}