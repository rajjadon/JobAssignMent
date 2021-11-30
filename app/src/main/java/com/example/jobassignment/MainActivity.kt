package com.example.jobassignment

import com.example.jobassignment.common.BaseActivity
import com.example.jobassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getActivityLayout() = R.layout.activity_main
}