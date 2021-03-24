package com.example.recyclerviewdemo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDAO {

    @Insert
    suspend fun addStudent(s : Student)

    @Query("Select * from student_table")
    suspend fun getAllStudent() : Array<Student>
}