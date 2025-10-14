package com.example.bitfit_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitfit_app.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.get(this)
        adapter = FoodAdapter()

        binding.rvFoods.layoutManager = LinearLayoutManager(this)
        binding.rvFoods.adapter = adapter

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, com.example.bitfit_app.CreateActivityEntry::class.java))
        }

        lifecycleScope.launch {
            db.foodDao().observeAll().collectLatest { entries ->
                adapter.submitList(entries)   // no named argument
            }
        }
    }
}
