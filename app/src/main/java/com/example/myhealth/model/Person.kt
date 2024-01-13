package com.example.myhealth.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) var id : Int,
    @ColumnInfo(name = "Name")var name : String,
    @ColumnInfo(name = "Age")var age : String,
    @ColumnInfo(name = "Weight")var weight : String,
    @ColumnInfo(name = "WorkType")var activityLevel : Int,
    @ColumnInfo(name = "LifeStyle")var lifestyle : Int,
    @ColumnInfo(name = "Water Intake")var waterConsumed : String,
    @ColumnInfo(name = "Water Recommended")var waterRecommend : String,
    @ColumnInfo(name = "Goal")var goal : String
)
