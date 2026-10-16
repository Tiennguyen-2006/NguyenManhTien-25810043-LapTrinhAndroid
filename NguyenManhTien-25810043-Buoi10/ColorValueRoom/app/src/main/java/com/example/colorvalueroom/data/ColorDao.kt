package com.example.colorvalueroom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Query("SELECT * FROM colors ORDER BY id DESC")
    fun observeAll(): Flow<List<ColorItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(color: ColorItem)

    @Delete
    suspend fun delete(color: ColorItem)

    @Query("SELECT COUNT(*) FROM colors")
    suspend fun count(): Int
}
