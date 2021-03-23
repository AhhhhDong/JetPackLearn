package com.wjd.jetpacklearn.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wjd.jetpacklearn.room.db.StudentDatabase
import com.wjd.jetpacklearn.room.entity.Student

class RoomViewModel(application: Application) : AndroidViewModel(application) {


    private val dataBase: StudentDatabase
    val studentListLiveData: LiveData<List<Student>>

    init {
        dataBase = StudentDatabase.getInstance(application)
        studentListLiveData = dataBase.studentDao.queryAllStudent()
    }

    fun addStudent(name: String, age: Int) {
        if (name.isEmpty()) return
        dataBase.studentDao.insertStudent(Student(name, age))
    }

    override fun onCleared() {
        super.onCleared()
        dataBase.close()
    }

    fun deleteStudent(name: String) {
        if (name.isEmpty()) return
    }

    fun editStudent(name: String, age: Int) {
        if (name.isEmpty()) return
    }


}