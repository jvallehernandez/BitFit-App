package com.example.bitfit_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bitfit_app.databinding.ActivityCreateEntryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateActivityEntry : AppCompatActivity() {
    private lateinit var binding: ActivityCreateEntryBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.get(this)

        binding.btnSave.setOnClickListener {
            val foodName = binding.etFoodName.text.toString()
            val caloriesText = binding.etCalories.text.toString()

            if (foodName.isNotEmpty() && caloriesText.isNotEmpty()) {
                val calories = caloriesText.toIntOrNull()
                if (calories != null) {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            val foodEntry = FoodEntry(
                                name = foodName,
                                calories = calories
                            )
                            db.foodDao().insert(foodEntry)
                        }
                        finish() // Close the activity after the DB operation is complete
                    }
                } else {
                    binding.etCalories.error = "Please enter a valid number"
                }
            } else {
                if (foodName.isEmpty()) {
                    binding.etFoodName.error = "Cannot be empty"
                }
                if (caloriesText.isEmpty()) {
                    binding.etCalories.error = "Cannot be empty"
                }
            }
        }
    }
}
