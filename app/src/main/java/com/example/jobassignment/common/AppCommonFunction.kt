package com.example.jobassignment.common

import android.widget.TextView

fun setDrawableIconOnLeft(textView: TextView, iconId: Int) {
    textView.setCompoundDrawablesWithIntrinsicBounds(iconId, 0, 0, 0)
}