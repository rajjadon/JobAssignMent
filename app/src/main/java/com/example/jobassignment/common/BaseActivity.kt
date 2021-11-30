package com.example.jobassignment.common

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import timber.log.Timber

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getActivityLayout())
        setContentView(binding.root)
    }

    abstract fun getActivityLayout(): Int

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val v = currentFocus
        try {
            if (v != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) &&
                v is EditText &&
                !v.javaClass.name.startsWith("android.webkit.")
            ) {
                val sourceCoordinates = IntArray(2)
                v.getLocationOnScreen(sourceCoordinates)
                val x = ev.rawX + v.getLeft() - sourceCoordinates[0]
                val y = ev.rawY + v.getTop() - sourceCoordinates[1]
                if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                    hideKeyboard(this)
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard(activity: Activity?) {
        if (activity != null && activity.window != null) {
            activity.window.decorView
            val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }
}