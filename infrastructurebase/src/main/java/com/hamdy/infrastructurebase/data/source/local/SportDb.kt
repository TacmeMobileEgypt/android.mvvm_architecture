package com.hamdy.infrastructurebase.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamdy.infrastructurebase.data.entities.Match

@Database(entities = [Match::class], version = 1)
abstract class SportDb : RoomDatabase() {

    abstract fun sportDao(): SportDao

    companion object {

        @Volatile
        private var INSTANCE: SportDb? = null

        fun getDatabase(context: Context): SportDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, SportDb::class.java, "sport_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}