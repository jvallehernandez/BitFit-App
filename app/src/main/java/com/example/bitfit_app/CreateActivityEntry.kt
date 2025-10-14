package com.example.bitfit_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateActivityEntry : AppCompatActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        db = AppDatabase.get(this)

        val etFood = findViewById<EditText>(R.id.etFood)
        val etCalories = findViewById<EditText>(R.id.etCalories)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = etFood.text.toString().trim()
            val cals = etCalories.text.toString().toIntOrNull()

            if (name.isNotEmpty() && cals != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    db.foodDao().insert(FoodEntry(name = name, calories = cals))
                }
                finish()
            } else {
                if (name.isEmpty()) etFood.error = "Required"
                if (cals == null) etCalories.error = "Numbers only"
            }
        }
    }
}
