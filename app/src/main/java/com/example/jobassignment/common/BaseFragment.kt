package com.example.jobassignment.common

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.jobassignment.R
import com.example.jobassignment.data.SessionManager
import com.example.jobassignment.databinding.CustomSnackbarLayoutBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), SnackBarMessageRules {

    lateinit var binding: T

    private lateinit var snackBar: Snackbar
    private lateinit var layout: Snackbar.SnackbarLayout
    private lateinit var customSnackBarLayoutBinding: CustomSnackbarLayoutBinding

    @Inject
    lateinit var sessionManager: SessionManager

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createSnackBar()
    }

    @SuppressLint("ShowToast")
    private fun createSnackBar() {
        snackBar = Snackbar.make(requireView(), "", Snackbar.LENGTH_LONG)
        layout = snackBar.view as Snackbar.SnackbarLayout

        customSnackBarLayoutBinding =
            CustomSnackbarLayoutBinding.inflate(LayoutInflater.from(requireContext()))

        layout.addView(customSnackBarLayoutBinding.root, 0)
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

    open fun shakeError(): TranslateAnimation? {
        val shake = TranslateAnimation(0F, 30F, 0F, 0F)
        shake.duration = 300
        shake.interpolator = CycleInterpolator(2F)
        return shake
    }

    override fun showInfoIconMessage(message: String) {
        setDrawableIconOnLeft(customSnackBarLayoutBinding.snackBarMessage, R.drawable.ic_info)
        customSnackBarLayoutBinding.snackBarMessage.text = message
        snackBar.show()
    }
}