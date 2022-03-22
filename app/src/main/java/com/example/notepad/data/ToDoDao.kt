package com.example.notepad.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notepad.data.models.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>


    // OnConflictStrategy.IGNORE  means when a new item comes to database,
    // witch is the same as the item we already have in our database,we ignore that
    // suspend is used from of function to tell to compiler that our function run inside coroutins(run in background)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)


    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
   suspend fun deleteItem(toDoData: ToDoData)

   @Query("DELETE FROM todo_table")
   suspend fun deleteAll()

}