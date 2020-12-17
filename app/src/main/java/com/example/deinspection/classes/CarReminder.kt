package com.example.deinspection.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "car_reminder",
    foreignKeys = arrayOf(
        ForeignKey(entity = Car::class ,parentColumns = arrayOf("licenseplate") ,childColumns = arrayOf("licenseplate") ,onDelete = ForeignKey.CASCADE ),
        ForeignKey(entity = Reminder::class ,parentColumns = arrayOf("id") ,childColumns = arrayOf("reminderId") ,onDelete = ForeignKey.CASCADE )
    )
    )
data class CarReminder(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    @ColumnInfo(index=true)val licenseplate : String,
    @ColumnInfo(index=true) val reminderId: Int = 0,

    )