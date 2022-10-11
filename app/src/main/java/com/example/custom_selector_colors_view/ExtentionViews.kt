package com.example.custom_selector_colors_view

import android.view.View

infix fun View.onclick(onClick: () -> Unit) {
    this.setOnClickListener {
        onClick()
    }
}