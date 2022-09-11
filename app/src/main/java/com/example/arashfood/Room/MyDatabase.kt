package com.example.arashfood.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.arashfood.Food

@Database(version = 1, exportSchema = false, entities = [Food::class])
abstract class MyDatabase: RoomDatabase() {

    abstract val foodDao:FoodDao

    companion object{

        private var database:MyDatabase?=null

    fun getDatabase(context: Context):MyDatabase {
        if (database==null)
        {
          database=Room.databaseBuilder(
              context.applicationContext,MyDatabase::class.java,
              "Fooddata"
          )   .allowMainThreadQueries()
              .build()
        }
        return database!!

       }
    }
}