package com.example.custom_selector_colors_view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.custom_selector_colors_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
   // lateinit var colorSelector: ColorSelector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);


       binding.selector.addColorSelectListener {color->
            Log.e(TAG, "onCreate:$color ", )
        }
      binding.sliderSelector.addListener {color->
      Log.e(TAG, "onCreate:$color ", )

  }

       binding.colorDialView.addListener { color->
           Log.e(TAG, "onCreate:$color ", )

       }

        }

    }
