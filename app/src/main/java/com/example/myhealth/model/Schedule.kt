package com.example.myhealth.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true) var id : Int,
    @ColumnInfo(name = "Quantity")var quantity : Int,
    @ColumnInfo(name = "Beverage")var beverageSelected : Int,
    @ColumnInfo(name = "Weather Permission")var weatherPermission : Boolean,

)