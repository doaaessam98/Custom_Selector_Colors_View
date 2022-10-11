package com.example.custom_selector_colors_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.example.custom_selector_colors_view.databinding.ColorSelectorBinding

class ColorSelector @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,defStyle:Int=0,defRes:Int = 0,
   ) : LinearLayout(context, attrs,defStyle,defRes) {
     private var binding: ColorSelectorBinding
    var listOfColors = listOf(Color.BLUE,Color.RED,Color.GREEN)
    var selectedColorIndex:Int=0

    var selectedColorValue:Int = android.R.color.transparent
    set(value) {
        var index=listOfColors.indexOf(value)
        if (index == -1){
            binding.ColorEnable.isChecked=false
            index=0
        }
        else{
            binding.ColorEnable.isChecked=true
        }
        selectedColorIndex=index
        setSelectedColor()
    }

    init {
      val typedArray = context.obtainStyledAttributes(attrs,R.styleable.ColorSelector)
       listOfColors=typedArray.getTextArray(R.styleable.ColorSelector_colors)
           .map {
               Color.parseColor(it.toString())
           }
        typedArray.resources
        orientation= HORIZONTAL
        val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
        binding=DataBindingUtil.inflate(inflater,R.layout.color_selector,this,true)
         setSelectedColor()
        binding.ColorSelectorArrowLeft onclick :: selectPreviousColor
        binding.ColorSelectorArrowRight onclick :: selectNextColor
        binding.ColorEnable.setOnCheckedChangeListener { compoundButton, isChecked ->
            broadCastColor()
        }
    }

    private fun selectNextColor() {
        if(selectedColorIndex==listOfColors.lastIndex){
            selectedColorIndex= 0
        }
        else{
            selectedColorIndex++
        }
        setSelectedColor()
        broadCastColor()
    }

    private fun selectPreviousColor() {
        if(selectedColorIndex==0){
            selectedColorIndex=listOfColors.lastIndex
        }
        else{
            selectedColorIndex--
        }
       setSelectedColor()
        broadCastColor()
    }

    private fun setSelectedColor(){
        binding.selectorColor.setBackgroundColor(listOfColors[selectedColorIndex])

    }
    var colorSelectedListener:ArrayList< (Int) -> Unit>? = arrayListOf()

    fun  addColorSelectListener(function:(Int)->Unit){
        this.colorSelectedListener?.add (function)
    }

    private fun broadCastColor(){
       var color =  if (binding.ColorEnable.isChecked){
            listOfColors[selectedColorIndex]}
        else{
            Color.TRANSPARENT
        }


      this.colorSelectedListener?.forEach { function ->
            function(color)
        }

    }

}