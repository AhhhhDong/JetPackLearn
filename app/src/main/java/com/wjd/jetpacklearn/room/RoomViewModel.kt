package com.wjd.jetpacklearn.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wjd.jetpacklearn.room.db.StudentDatabase
import com.wjd.jetpacklearn.room.entity.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomViewModel(application: Application) : AndroidViewModel(application) {


    private val dataBase: StudentDatabase
    val studentListLiveData: LiveData<List<Student>>

    init {
        dataBase = StudentDatabase.getInstance(application)
        studentListLiveData = dataBase.studentDao.queryAllStudent()
    }

    fun queryAllStudent() {

    }

    fun addStudent(name: String, age: Int) {
        if (name.isEmpty()) return
        Thread {
            dataBase.studentDao.insertStudent(Student(name, age))
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        dataBase.close()
    }

    fun deleteStudent(name: String) {
        if (name.isEmpty()) return
        GlobalScope.launch(context = Dispatchers.IO) {
            dataBase.studentDao.run {
                queryStudentByName(name)?.let { deleteStudent(it) }
            }
        }
    }

    fun editStudent(name: String, age: Int) {
        if (name.isEmpty()) return
    }


}