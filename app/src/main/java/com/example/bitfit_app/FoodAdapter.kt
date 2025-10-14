package com.example.bitfit_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter : ListAdapter<FoodEntry, FoodAdapter.VH>(DIFF) {
    class VH(v: View) : RecyclerView.ViewHolder(v) {
        private val name: TextView = v.findViewById(R.id.tvName)
        private val cals: TextView = v.findViewById(R.id.tvCalories)
        fun bind(item: FoodEntry) {
            name.text = item.name
            cals.text = item.calories.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<FoodEntry>() {
            override fun areItemsTheSame(a: FoodEntry, b: FoodEntry) = a.id == b.id
            override fun areContentsTheSame(a: FoodEntry, b: FoodEntry) = a == b
        }
    }
}
