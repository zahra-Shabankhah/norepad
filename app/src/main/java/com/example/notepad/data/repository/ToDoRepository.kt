package com.example.notepad.data.repository

import androidx.lifecycle.LiveData
import com.example.notepad.data.ToDoDao
import com.example.notepad.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData : LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
}