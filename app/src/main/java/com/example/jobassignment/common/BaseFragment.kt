/*
 * Created by Dheeraj on 22/09/21, 7:14 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 22/09/21, 6:52 PM
 */

package com.example.jobassignment.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        implementApiCallsDataReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setUpBindingVariables()
        setClickListener()
    }

    abstract fun getFragmentLayout(): Int
    abstract fun implementApiCallsDataReceiver()
    abstract fun setUpBindingVariables()
    abstract fun setClickListener()

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
    }
}