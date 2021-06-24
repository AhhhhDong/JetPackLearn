package com.wjd.jetpacklearn.room.entity

import androidx.room.*

@Entity(tableName = "student",indices = [Index(value = ["date"],unique = true)])
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT, defaultValue = "")
    var name: String,
    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    var age: Int
) {
    @Ignore
    constructor(name: String, age: Int) : this(null, name, age)
}