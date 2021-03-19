package com.wjd.jetpacklearn.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wjd.jetpacklearn.common.SingletonHolder
import com.wjd.jetpacklearn.room.entity.Student

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    companion object : SingletonHolder<StudentDatabase, Context>({
        Room.databaseBuilder(it, StudentDatabase::class.java, StudentDatabase.DB_NAME).build()
    }) {
        private const val DB_NAME = "student_db"
    }

    abstract val studentDao: StudentDao
}