package com.example.jobassignment.ui.onBoardScreenFragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jobassignment.R
import com.example.jobassignment.common.AppConstant.EMAIL
import com.example.jobassignment.common.AppConstant.PASSWORD
import com.example.jobassignment.common.BaseFragment
import com.example.jobassignment.databinding.FragmentOnBoardScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardScreenFragment : BaseFragment<FragmentOnBoardScreenBinding>() {
    override fun getFragmentLayout() = R.layout.fragment_on_board_screen

    override fun implementApiCallsDataReceiver() {
        /*Not yet implemented*/
    }

    override fun setUpBindingVariables() {
        /*Not yet implemented*/
    }

    override fun setClickListener() {

        binding.button.setOnClickListener {

            if (!binding.etEmail.text.toString().equals(EMAIL, true)) {
                binding.etEmail.startAnimation(shakeError())
                showInfoIconMessage("Please enter valid email")
            } else if (!binding.etPass.text.toString().equals(PASSWORD, true)) {
                binding.etPass.startAnimation(shakeError())
                showInfoIconMessage("Please enter valid password")
            } else lifecycleScope.launchWhenResumed {
                sessionManager.saveSession(true)
                findNavController().navigate(R.id.action_onBoardScreenFragment_to_homeScreenFragment)
            }
        }
    }
}