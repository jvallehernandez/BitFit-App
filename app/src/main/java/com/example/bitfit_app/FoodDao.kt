package com.example.bitfit_app
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_entries ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<FoodEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: FoodEntry)

    @Query("DELETE FROM food_entries")
    suspend fun clear()
}
