package com.example.deinspection.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.deinspection.classes.Car

@Dao
interface CarDao {
    @Query("Select * from car")
    fun getAll():List<Car>
    @Query("Select * from car where licenseplate = :licenseplate  ")
    fun getByLicense(licenseplate:String):Car
    @Insert
    fun insertCar(vararg car: Car):List<String> // insere um ou mais carros,retorna sempre lista de plates mesmo que seja só 1 carro
}