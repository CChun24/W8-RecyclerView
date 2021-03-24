package com.example.recyclerviewdemo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "student_table")
class Student (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val imgSource : Int,
    val name: String,
    val programme : String
)
