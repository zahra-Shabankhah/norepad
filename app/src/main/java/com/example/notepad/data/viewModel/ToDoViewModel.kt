package com.example.notepad.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notepad.data.ToDoDatabase
import com.example.notepad.data.models.ToDoData
import com.example.notepad.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application):AndroidViewModel(application) {
   // private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository : ToDoRepository
     val getAllData : LiveData<List<ToDoData>>

    //initialize our repository
    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData
    }

    fun insertData(toDoData: ToDoData){
// viewModelScope is a part of kotlin coroutines.
//  so basically we are running our background thread and
//  we want to run this uncertain data function from a background thread

        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(toDoData)
        }
    }
}