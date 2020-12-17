package com.example.deinspection.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deinspection.classes.Car
import com.example.deinspection.classes.Reminder
import com.example.deinspection.classes.CarReminder
import com.example.deinspection.database.dao.CarDao
import com.example.deinspection.database.dao.CarReminderDao
import com.example.deinspection.database.dao.ReminderDao

@Database(
        entities= arrayOf(
                Car::class,
                Reminder::class,
                CarReminder::class
        ),version = 1
)
abstract class MyRoom : RoomDatabase() {
        abstract fun carDao():CarDao
        abstract fun reminderDao(): ReminderDao
        abstract fun carReminderDao(): CarReminderDao
}