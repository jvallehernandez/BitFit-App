package com.example.bitfit_app

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitfit_app.databinding.FragmentLogBinding
import kotlinx.coroutines.launch


class LogFragment : Fragment() {
    private var _binding: FragmentLogBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private val adapter = FoodAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.get(requireContext())

        binding.rvFoods.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFoods.adapter = adapter

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(requireContext(), CreateActivityEntry::class.java))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            db.foodDao().observeAll().collect { entries ->
                adapter.submitList(entries)
            }
        }
    }


    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}
