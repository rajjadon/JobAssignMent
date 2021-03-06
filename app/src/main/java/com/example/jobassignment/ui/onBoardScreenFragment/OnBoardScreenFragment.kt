package com.example.jobassignment.ui.onBoardScreenFragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.jobassignment.R
import com.example.jobassignment.common.AppConstant.EMAIL
import com.example.jobassignment.common.AppConstant.PASSWORD
import com.example.jobassignment.common.BaseFragment
import com.example.jobassignment.common.FragmentNavigator
import com.example.jobassignment.databinding.FragmentOnBoardScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OnBoardScreenFragment : BaseFragment<FragmentOnBoardScreenBinding>() {
    override fun getFragmentLayout() = R.layout.fragment_on_board_screen

    override fun implementApiCallsDataReceiver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sessionManager.isLoggedIn.collect {
                    if (it)
                        findNavController().navigate(FragmentNavigator.OPEN_HOME_SCREEN_FROM_ON_BOARD_SCREEN.navigateActionId)
                }
            }
        }
    }

    override fun setUpBindingVariables() {
        /*Not yet implemented*/
    }

    override fun setClickListener() {
        binding.login.setOnClickListener {
            if (!binding.etEmail.text.toString().trim().equals(EMAIL, true)) {
                binding.etEmail.startAnimation(shakeError())
                showInfoIconMessage("Please enter valid email")
            } else if (!binding.etPass.text.toString().trim().equals(PASSWORD, true)) {
                binding.etPass.startAnimation(shakeError())
                showInfoIconMessage("Please enter valid password")
            } else {
                lifecycleScope.launch {
                    sessionManager.saveSession(true)
                }
            }
        }
    }
}