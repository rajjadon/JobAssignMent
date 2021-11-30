package com.example.jobassignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jobassignment.databinding.FragmentOnBoardScreenBinding

class OnBoardScreenFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOnBoardScreenBinding.inflate(inflater)
        return binding.root
    }
}