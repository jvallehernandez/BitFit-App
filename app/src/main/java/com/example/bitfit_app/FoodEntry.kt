package com.example.bitfit_app
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_entries")
data class FoodEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val calories: Int,
    val createdAt: Long = System.currentTimeMillis()
)
