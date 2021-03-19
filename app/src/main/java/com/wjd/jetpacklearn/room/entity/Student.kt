package com.wjd.jetpacklearn.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT, defaultValue = "")
    var name: String,
    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    var age: Int
) {

}