package com.example.bitfit_app

import androidx.room.*
import kotlinx.coroutines.flow.Flow

data class CalorieStats(
    @ColumnInfo(name = "avg_cal") val avg: Double?,
    @ColumnInfo(name = "min_cal") val min: Int?,
    @ColumnInfo(name = "max_cal") val max: Int?
)

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_entries ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<FoodEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: FoodEntry)

    // NEW:
    @Query("""
        SELECT 
            AVG(calories) AS avg_cal, 
            MIN(calories) AS min_cal, 
            MAX(calories) AS max_cal 
        FROM food_entries
    """)
    fun observeStats(): Flow<CalorieStats>

    @Query("DELETE FROM food_entries")
    suspend fun clear()
}
