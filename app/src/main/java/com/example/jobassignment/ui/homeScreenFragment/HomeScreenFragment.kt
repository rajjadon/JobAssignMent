package com.example.jobassignment.ui.homeScreenFragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jobassignment.R
import com.example.jobassignment.common.BaseFragment
import com.example.jobassignment.common.FragmentNavigator
import com.example.jobassignment.databinding.FragmentHomescreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<FragmentHomescreenBinding>() {

    override fun getFragmentLayout() = R.layout.fragment_homescreen

    override fun implementApiCallsDataReceiver() {
        /*Not yet implemented*/
    }

    override fun setUpBindingVariables() {
        /*Not yet implemented*/
    }

    override fun setClickListener() {
        binding.logout.setOnClickListener {
            lifecycleScope.launch {
                sessionManager.saveSession(false)
                findNavController().navigate(FragmentNavigator.OPEN_ON_BOARD_SCREEN_FROM_HOME_SCREEN.navigateActionId)
            }
        }
    }
}