package com.example.notepad.data

import android.content.Context
import androidx.room.*
import com.example.notepad.data.models.ToDoData

/* Create database */

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase:RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {

        //Volatile means Writs to this field are immediately made visible to other threads
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
   /* fun getDatabase(context: Context): ToDoDatabase =
    INSTANCE ?: synchronized(this) {
        INSTANCE
            ?: buildDatabase(context).also { INSTANCE = it }
    }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ToDoDatabase::class.java, "todo_database"
            ).build()
    }

}*/