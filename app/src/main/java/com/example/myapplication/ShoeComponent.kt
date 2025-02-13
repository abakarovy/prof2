package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class ShoeComponent(context: Context) : LinearLayout(context) {
    init {
        inflate(context, R.layout.shoe_component, this)

    }
}