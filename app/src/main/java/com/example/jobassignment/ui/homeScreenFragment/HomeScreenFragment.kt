package com.example.jobassignment.ui.homeScreenFragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.jobassignment.R
import com.example.jobassignment.common.BaseFragment
import com.example.jobassignment.common.FragmentNavigator
import com.example.jobassignment.databinding.FragmentHomescreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<FragmentHomescreenBinding>() {

    override fun getFragmentLayout() = R.layout.fragment_homescreen

    override fun implementApiCallsDataReceiver() {
        /*Not yet implemented*/
    }

    override fun setUpBindingVariables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sessionManager.isLoggedIn.collect {
                    if (!it) {
                        sessionManager.deleteSession()
                        findNavController().navigate(FragmentNavigator.OPEN_ON_BOARD_SCREEN_FROM_HOME_SCREEN.navigateActionId)
                    }
                }
            }
        }
    }

    override fun setClickListener() {
        binding.logout.setOnClickListener {
            lifecycleScope.launch {
                sessionManager.saveSession(false)
            }
        }
    }
}