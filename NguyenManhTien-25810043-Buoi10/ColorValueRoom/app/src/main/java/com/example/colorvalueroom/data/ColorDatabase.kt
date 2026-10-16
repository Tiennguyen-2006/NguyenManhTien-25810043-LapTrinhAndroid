package com.example.colorvalueroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ColorItem::class], version = 1, exportSchema = false)
abstract class ColorDatabase : RoomDatabase() {
    abstract fun colorDao(): ColorDao

    companion object {
        @Volatile
        private var instance: ColorDatabase? = null

        fun getDatabase(context: Context): ColorDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    ColorDatabase::class.java,
                    "colors.db"
                ).build().also { instance = it }
            }
        }
    }
}
