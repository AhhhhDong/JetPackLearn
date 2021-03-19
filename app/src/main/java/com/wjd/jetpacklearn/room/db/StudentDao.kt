package com.wjd.jetpacklearn.room.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wjd.jetpacklearn.room.entity.Student

//Data Access objects
@Dao
interface StudentDao {

    @Insert(entity = Student::class)
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("SELECT * FROM student WHERE name = :name")
    fun queryStudentByName(name: String): Student

    @Query("SELECT * FROM student")
    fun queryAllStudent(): LiveData<List<Student>>
}