package com.example.bitfit_app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.bitfit_app.databinding.FragmentDashboardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.get(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            db.foodDao().observeStats().collect { s ->
                binding.tvAvg.text = "Average Calories:    ${s.avg?.toInt() ?: 0}"
                binding.tvMin.text = "Minimum Calories:    ${s.min ?: 0}"
                binding.tvMax.text = "Maximum Calories:    ${s.max ?: 0}"
            }
        }

        binding.btnClear.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) { db.foodDao().clear() }
        }

        binding.btnAddFood.setOnClickListener {
            val intent = Intent(requireContext(), CreateActivityEntry::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
