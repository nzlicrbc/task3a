package com.example.task3a.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task3a.databinding.FragmentGuessBinding

class GuessFragment : Fragment() {
    private var _binding: FragmentGuessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuessBinding.inflate(inflater, container, false)
        return binding.root
    }
}